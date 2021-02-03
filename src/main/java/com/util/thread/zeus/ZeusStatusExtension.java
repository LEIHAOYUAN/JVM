package com.util.thread.zeus;

import java.util.Map;

public interface ZeusStatusExtension {
    String getId();

    String getDescription();

    Map<String, String> getProperties();
}