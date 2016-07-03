package com.screwfix.claim.statistics.services;

import com.screwfix.claim.statistics.StatisticsConfiguration;
import com.screwfix.claim.statistics.models.Action;
import com.screwfix.claim.statistics.models.Build;
import com.screwfix.claim.statistics.models.BuildXml;

import javax.inject.Inject;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.abs;
import static java.nio.file.FileSystems.getDefault;
import static java.nio.file.Files.walk;
import static java.time.Duration.between;
import static java.time.LocalDateTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.*;
import static java.util.stream.StreamSupport.stream;
import static javax.xml.bind.JAXB.unmarshal;

public class XmlLoader {

    private static final String DATE_PATTERN = "(\\d{4})-(\\d{2})-(\\d{2})_(\\d{2})-(\\d{2})-(\\d{2})";
    private static final DateTimeFormatter FORMATTER = ofPattern("yyyy-MM-dd_HH-mm-ss");
    private static final PathMatcher EXT_MATCHER = getDefault().getPathMatcher("glob:**build.xml");
    private static final Pattern JOB_NAME_PATTERN = Pattern.compile("((\\w*[^\\\\])(?=\\\\builds))");

    private StatisticsConfiguration configuration;

    public Map<String, List<Build>> loadXml() {
        try {
            return walk(Paths.get(getJobsPath()))
                    .filter(EXT_MATCHER::matches)
                    .filter(this::matchByFolderName)
                    .collect(groupingBy(this::resolveJobName, mapping(this::transform, toList())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String resolveJobName(Path path) {
        Matcher matcher = JOB_NAME_PATTERN.matcher(path.toString());
        if (matcher.find()) return matcher.group();
        else return "";
    }

    private boolean matchByFolderName(Path path) {
        LocalDateTime jobDay = getJobDate(path);
        LocalDateTime today = LocalDateTime.now();
        long days = between(today, jobDay).toDays();
        return abs(days) < getDaysToAnalyze();
    }

    private LocalDateTime getJobDate(Path path) {
        return stream(path.getParent().spliterator(), false)
                .map(Path::toString)
                .filter(p -> p.matches(DATE_PATTERN))
                .map(input -> parse(input, FORMATTER))
                .findFirst().get();
    }

    private Build transform(Path path) {
        BuildXml buildXml = unmarshal(path.toFile(), BuildXml.class);
        Action action = buildXml.getActions()
                .stream()
                .findFirst()
                .orElse(new Action());
        return new Build()
                .setBuildDate(getJobDate(path))
                .setResult(buildXml.getResult())
                .setClaimed(action.isClaimed())
                .setClaimedBy(action.getClaimedBy())
                .setReason(action.getReason())
                .setJobName(resolveJobName(path));
    }

    @Inject
    public void setConfiguration(StatisticsConfiguration configuration) {
        this.configuration = configuration;
    }

    private String getJobsPath() {
        return configuration.getJobsPath();
    }

    private int getDaysToAnalyze() {
        return configuration.getAnalyzeDays();
    }
}
