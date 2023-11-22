package org.kainos.ea.team2.cli;

<<<<<<< HEAD
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
=======

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Describes a band level within the system.
 */
public class BandLevel {
    /**
     * The id of the band level.
     */
    private final int bandLevelId;

    /**
     * The name of the band level.
     */
    private final String bandName;

    /**
     * Creates a new band level object.
     * @param bandLevelId the id of the band level
     * @param bandName the name of the band level
     */
    @JsonCreator
    public BandLevel(
            @JsonProperty("bandlevel_id") final int bandLevelId,
            @JsonProperty("band_name") final String bandName
    ) {
        this.bandLevelId = bandLevelId;
        this.bandName = bandName;
    }

    /**
     * Gets the id of the band level.
     * @return the id of the band level
>>>>>>> origin/main
     */
    public int getBandLevelId() {
        return bandLevelId;
    }

    /**
<<<<<<< HEAD
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


=======
     * Gets the name of the band level.
     * @return the name of the band level
     */
    public String getBandName() {
        return bandName;
    }
>>>>>>> origin/main
}
