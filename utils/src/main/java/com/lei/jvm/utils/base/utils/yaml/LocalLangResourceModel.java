package com.lei.jvm.utils.base.utils.yaml;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocalLangResourceModel implements Serializable {

    private static final long serialVersionUID = -3361551538682603024L;

    public LocalLangResourceModel(){}

    private String value;

    private long version;

}