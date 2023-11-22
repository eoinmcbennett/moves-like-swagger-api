package org.kainos.ea.team2.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BandLevel {

    /**
     * ID of band level.
     */
    private int bandLevelId;

    /**
     * name of band level.
     */
    private String bandLevelName;

    /**
     * Constructor with args - creates a band level with a name and id.
     * @param bandLevelId
     * @param bandLevelName
     */
    @JsonCreator
    public BandLevel(@JsonProperty("jobId") final int bandLevelId,
               @JsonProperty("jobName") final String bandLevelName) {
        this.bandLevelId = bandLevelId;
        this.bandLevelName = bandLevelName;
    }

    /**
     * get ID of band level.
     * @return int bandLevelID
     */
    public int getBandLevelId() {
        return bandLevelId;
    }

    /**
     * set ID of bandLevel.
     * @param bandLevelId
     */
    public void setBandLevelId(final int bandLevelId) {

        this.bandLevelId = bandLevelId;
    }

    /**
     * get name of band level.
     * @return String bandLevelName
     */
    public String getBandLevelName() {
        return bandLevelName;
    }

    /**
     * set name of band level.
     * @param bandLevelName
     */
    public void setBandLevelName(final String bandLevelName) {

        this.bandLevelName = bandLevelName;
    }


}
