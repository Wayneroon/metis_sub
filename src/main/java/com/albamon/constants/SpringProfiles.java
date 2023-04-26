package com.albamon.constants;

import lombok.NoArgsConstructor;
import org.springframework.core.env.Environment;

import java.util.stream.Stream;

/**
 * Spring Profiles
 * <p>
 * 로컬: local
 * 운영 : pr
 * 개발 : dv
 * </p>
 */
@NoArgsConstructor
public final class SpringProfiles {
    // 서버 종류
    public static final String LOCAL = "local";
    public static final String DV = "dv";
    public static final String PR = "pr";

    /**
     * 활성화된 프로필인가?
     */
    public static boolean isActive(Environment env, String... targetProfiles) {
        return Stream.of(env.getActiveProfiles())
                .anyMatch(p -> {
                    for (String profile : targetProfiles) {
                        if (p.equals(profile)) {
                            return true;
                        }
                    }
                    return false;
                });
    }

    public static String getChiefActiveProfile(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length < 1) {
            throw new IllegalStateException("Active Profile Not Found");
        }
        return profiles[0];
    }
}
