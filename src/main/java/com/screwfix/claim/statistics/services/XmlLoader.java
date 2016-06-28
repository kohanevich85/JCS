package com.screwfix.claim.statistics.services;

import com.screwfix.claim.statistics.StatisticsConfiguration;
import com.screwfix.claim.statistics.models.Build;

import javax.inject.Inject;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Math.*;
import static java.nio.file.FileSystems.getDefault;
import static java.nio.file.Files.readAttributes;
import static java.nio.file.Files.walk;
import static java.time.Duration.between;
import static java.time.LocalDateTime.parse;
import static java.time.ZoneId.systemDefault;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static javax.xml.bind.JAXB.unmarshal;

public class XmlLoader {

    private static final String PATTERN = "(\\d{4})-(\\d{2})-(\\d{2})_(\\d{2})-(\\d{2})-(\\d{2})";
    private static final DateTimeFormatter FORMATTER = ofPattern("yyyy-MM-dd_HH-mm-ss");
    private static final PathMatcher EXT_MATCHER = getDefault().getPathMatcher("glob:**build.xml");
    private StatisticsConfiguration configuration;

    public List<Build> loadXml() {
        try {
            return walk(Paths.get(getJobsPath()))
                    .filter(EXT_MATCHER::matches)
                    .filter(this::matchByFolderName)
                    .map(this::transform)
                    .collect(toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean matchByCreationDate(Path path) {
        try {
            FileTime creationTime = readAttributes(path, BasicFileAttributes.class).creationTime();
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime jobDay = creationTime.toInstant().atZone(systemDefault()).toLocalDateTime();
            long days = between(today, jobDay).toDays();
            return abs(days) < getDaysToAnalyze();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private boolean matchByFolderName(Path path) {
        LocalDateTime jobDay = stream(path.getParent().spliterator(), false)
                .map(Path::toString)
                .filter(p -> p.matches(PATTERN))
                .map(input -> parse(input, FORMATTER))
                .findFirst().get();
        LocalDateTime today = LocalDateTime.now();
        long days = between(today, jobDay).toDays();
        return abs(days) < getDaysToAnalyze();
    }

    private Build transform(Path path) {
        Build build = unmarshal(path.toFile(), Build.class);
        LocalDateTime jobStartDate = stream(path.getParent().spliterator(), false)
                .map(Path::toString)
                .filter(p -> p.matches(PATTERN))
                .map(input -> parse(input, FORMATTER))
                .findFirst().get();
        return build.setBuildDate(jobStartDate);
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
