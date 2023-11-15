package org.kainos.ea.team2.cli;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BandLevel {
    private final int bandLevelId;
    private final String bandName;

    @JsonCreator
    public BandLevel(
            @JsonProperty("bandlevel_id") final int bandLevelId,
            @JsonProperty("band_name") final String bandName
    ){
        this.bandLevelId = bandLevelId;
        this.bandName = bandName;
    }

    public int getBandLevelId() {
        return bandLevelId;
    }

    public String getBandName() {
        return bandName;
    }
}
