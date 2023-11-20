package org.kainos.ea.team2.cli;


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
     */
    public int getBandLevelId() {
        return bandLevelId;
    }

    /**
     * Gets the name of the band level.
     * @return the name of the band level
     */
    public String getBandName() {
        return bandName;
    }
}
