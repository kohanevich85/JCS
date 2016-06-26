package com.screwfix.claim.statistics.services;

import com.screwfix.claim.statistics.StatisticsConfiguration;
import com.screwfix.claim.statistics.models.Build;

import javax.inject.Inject;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.nio.file.Files.readAttributes;
import static java.nio.file.Files.walk;
import static java.util.stream.Collectors.toList;
import static javax.xml.bind.JAXB.unmarshal;

public class XmlLoader {

    public static final Function<Path, Build> xmlTransformer = path -> unmarshal(path.toFile(), Build.class);
    private StatisticsConfiguration configuration;

    public List<Build> loadXml() {
        try {
            int analyzeDays = configuration.getAnalyzeDays();
            String jobsPath = configuration.getJobsPath();
            Predicate<Path> filter = path -> matchName(path) && matchPathByCreationDate(path, analyzeDays);
            return walk(Paths.get(jobsPath))
                    .filter(filter)
                    .map(xmlTransformer)
                    .collect(toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean matchName(Path path) {
        return path.getFileName().toString().contains("build.xml");
    }

    private boolean matchPathByCreationDate(Path p, int analyzeDays) {
        try {
            BasicFileAttributes attr = readAttributes(p, BasicFileAttributes.class);
            FileTime creationTime = attr.creationTime();
            LocalDate today = LocalDate.now();
            LocalDate jobDay = creationTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long days = Duration.between(today.atTime(0, 0), jobDay.atTime(0, 0)).toDays();
            return (Math.abs(days) < analyzeDays);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Inject
    public void setConfiguration(StatisticsConfiguration configuration) {
        this.configuration = configuration;
    }
}
