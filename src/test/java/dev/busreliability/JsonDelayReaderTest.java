package dev.busreliability;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class JsonDelayReaderTest {

    @Test
    void testSingleBusDelay() throws IOException {
        String json = """
                {
                  "entity": [
                    {
                      "trip_update": {
                        "trip": {
                          "route_id": "1 160 d"
                        },
                        "stop_time_update": [
                          {
                            "arrival": {
                              "delay": 120
                            }
                          }
                        ]
                      }
                    }
                  ]
                }
                """;
        ArrayList<BusDelay> delays = JsonDelayReader.extractDelays(json);
        assertEquals(1, delays.size());
        assertEquals(delays.get(0).getDelaySeconds(), 120);
    }

    @Test
    void testMultipleStopDelays() throws IOException {
        String json = """
                {
                  "entity": [
                    {
                      "trip_update": {
                        "trip": {
                          "route_id": "1 160 d"
                        },
                        "stop_time_update": [
                          {
                            "arrival": {
                              "delay": 120
                            }
                          },
                          {
                            "arrival": {
                              "delay": 60
                            }
                          },
                          {
                            "arrival": {
                              "delay": 360
                            }
                          }
                        ]
                      }
                    }
                  ]
                }
                """;
        ArrayList<BusDelay> delays = JsonDelayReader.extractDelays(json);
        assertEquals(3, delays.size());
        assertEquals(delays.get(0).getDelaySeconds(), 120);
        assertEquals(delays.get(1).getDelaySeconds(), 60);
        assertEquals(delays.get(2).getDelaySeconds(), 360);
    }

    @Test
    void testMultipleTrips() throws IOException {
        String json = """
                {
                  "entity": [
                    {
                      "trip_update": {
                        "trip": {
                          "route_id": "1 160 d"
                        },
                        "stop_time_update": [
                          {
                            "arrival": {
                              "delay": 120
                            }
                          }
                        ]
                      }
                    },
                    {
                      "trip_update": {
                        "trip": {
                          "route_id": "1 168 d"
                        },
                        "stop_time_update": [
                          {
                            "arrival": {
                              "delay": 360
                            }
                          }
                        ]
                      }
                    }
                  ]
                }
                """;
        ArrayList<BusDelay> delays = JsonDelayReader.extractDelays(json);
        assertEquals(2, delays.size());
        assertEquals(delays.get(0).getDelaySeconds(), 120);
        assertEquals(delays.get(1).getDelaySeconds(), 360);
    }




}
