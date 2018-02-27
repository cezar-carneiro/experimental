package com.oproom.model.database;

import java.util.List;

/**
 * Created by Cezar Carneiro on 27/02/2018.
 */

public class DatabaseInfo {

    private String databaseName;
    private String databasePath;
    private Integer version;
    private Integer maximumSize;
    private Integer pageSize;
    private List<String> tables;
    private List<String> views;
    private List<String> indices;

}
