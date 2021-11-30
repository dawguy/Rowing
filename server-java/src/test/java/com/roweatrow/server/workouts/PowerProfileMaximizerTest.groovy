package com.roweatrow.server.workouts

import spock.lang.Specification

import javax.security.auth.Subject

class PowerProfileMaximizerTest extends Specification {
    PowerProfileMaximizer powerProfileMaximizer = new PowerProfileMaximizer()

    def setup(){}

    private List<PowerProfileSegment> getUnitSegments(){
        return [
                new PowerProfileSegment(200, 0, 1),
                new PowerProfileSegment(300,1,1),
                new PowerProfileSegment(250,2,1),
                new PowerProfileSegment(300, 3,1)
        ]
    }

    private List<PowerProfileSegment> getTwoHumps(){
        return [
                new PowerProfileSegment(100,0,1),
                new PowerProfileSegment(200,1,1),
                new PowerProfileSegment(250,2,1),
                new PowerProfileSegment(150,3,1),
                new PowerProfileSegment(300,4,1),
                new PowerProfileSegment(100,5,1),
        ]
    }

    private List<PowerProfileSegment> getLargeList(){
        List<PowerProfileSegment> list = new ArrayList<>();

        for(int i = 0; i < 1000; i++){
            list.add(new PowerProfileSegment(300, i, 1));
        }
        for(int i = 1000; i < 2000; i++){
            list.add(new PowerProfileSegment(0, i, 1));
        }
        for(int i = 2000; i < 3000; i++){
            list.add(new PowerProfileSegment(400, i, 1));
        }
        for(int i = 3000; i < 4000; i++){
            list.add(new PowerProfileSegment(100, i, 1));
        }

        return list
    }

    private List<PowerProfileSegment> getHugeList(){
        List<PowerProfileSegment> list = new ArrayList<>();

        for(int i = 0; i < 10000; i++){
            list.add(new PowerProfileSegment(300, i, 1));
        }
        for(int i = 10000; i < 20000; i++){
            list.add(new PowerProfileSegment(0, i, 1));
        }
        for(int i = 20000; i < 30000; i++){
            list.add(new PowerProfileSegment(400, i, 1));
        }
        for(int i = 30000; i < 40000; i++){
            list.add(new PowerProfileSegment(100, i, 1));
        }

        return list
    }

    private List<PowerProfileSegment> getHugerList(){
        List<PowerProfileSegment> list = new ArrayList<>();

        for(int i = 0; i < 100000; i++){
            list.add(new PowerProfileSegment(300, i, 1));
        }
        for(int i = 100000; i < 200000; i++){
            list.add(new PowerProfileSegment(0, i, 1));
        }
        for(int i = 200000; i < 300000; i++){
            list.add(new PowerProfileSegment(400, i, 1));
        }
        for(int i = 300000; i < 400000; i++){
            list.add(new PowerProfileSegment(100, i, 1));
        }

        return list
    }


    def "Maximum segment is found"(){
        given: "A list of power profile segments"
        var unitSegments = getUnitSegments()
        var twoHumps = getTwoHumps()
        when: "getMaxPowerSegment is called"
        var unitSegmentResult = powerProfileMaximizer.getMaxPowerSegment(unitSegments)
        var twoHumpsResult = powerProfileMaximizer.getMaxPowerSegment(twoHumps)
        var emptyResult = powerProfileMaximizer.getMaxPowerSegment([])
        then:
        1 == unitSegmentResult
        4 == twoHumpsResult
        -1 == emptyResult
    }

    def "Unit Segments Maximums Can Be Calculated"(){
        given: "A list of unit profile segments"
        var unitSegments = getUnitSegments()
        when: "calculateBestPower is called"
        var result = powerProfileMaximizer.calculateBestPower(unitSegments)
        PowerProfileMaximizer.printTimesChecked()
        then:
        300 == result.get(0).getPower()
        1 == result.get(0).getStartTime()
        275 == result.get(1).getPower()
        1 == result.get(1).getStartTime()
        283.3333333333333 == result.get(2).getPower()
        1 == result.get(2).getStartTime()
        262.5 == result.get(3).getPower()
        0 == result.get(3).getStartTime()
    }

    def "Two Humps Segments Maximums Can Be Calculated"(){
        given: "A list of unit profile segments"
        var unitSegments = getTwoHumps()
        when: "calculateBestPower is called"
        var result = powerProfileMaximizer.calculateBestPower(unitSegments)
        then:
        300 == result.get(0).getPower()
        4 == result.get(0).getStartTime()
    }

    def "Large List Segment Maximums Can Be Calculated"(){
        given: "A very large list of unit profile segments"
        var unitSegments = getLargeList()
        when: "calculateBestPower is called"
        var result = powerProfileMaximizer.calculateBestPower(unitSegments)
        PowerProfileMaximizer.printTimesChecked()
        then:
        0.1 > Math.abs(400 - result.get(0).getPower())
        2000 == result.get(0).getStartTime()
        0.1 > Math.abs(400 - result.get(999).getPower())
        2000 == result.get(999).getStartTime()
        0.1 > Math.abs(250 - result.get(1999).getPower())
        2000 == result.get(1999).getStartTime()
        0.1 > Math.abs(233.33333333 - result.get(2999).getPower())
        0 == result.get(2999).getStartTime()
        0.1 > Math.abs(200 - result.get(3999).getPower())
        0 == result.get(3999).getStartTime()
    }

    def "Huge List Segment Maximums Can Be Calculated"(){
        given: "A huge list of unit profile segments"
        var unitSegments = getHugeList()
        when: "calculateBestPower is called"
        var result = powerProfileMaximizer.calculateBestPower(unitSegments)
        PowerProfileMaximizer.printTimesChecked()
        then:
        0.1 > Math.abs(400 - result.get(0).getPower())
        20000 == result.get(0).getStartTime()
        0.1 > Math.abs(400 - result.get(9999).getPower())
        20000 == result.get(9999).getStartTime()
        0.1 > Math.abs(250 - result.get(19999).getPower())
        20000 == result.get(19999).getStartTime()
        0.1 > Math.abs(233.33333333 - result.get(29999).getPower())
        0 == result.get(29999).getStartTime()
        0.1 > Math.abs(200 - result.get(39999).getPower())
        0 == result.get(39999).getStartTime()
    }

    def "Huger List Segment Maximums Can Be Calculated"(){
        given: "A huger list of unit profile segments"
        var unitSegments = getHugerList()
        when: "calculateBestPower is called"
        var result = powerProfileMaximizer.calculateBestPower(unitSegments)
        PowerProfileMaximizer.printTimesChecked()
        then:
        0.1 > Math.abs(400 - result.get(0).getPower())
        200000 == result.get(0).getStartTime()
        0.1 > Math.abs(400 - result.get(99999).getPower())
        200000 == result.get(99999).getStartTime()
        0.1 > Math.abs(250 - result.get(199999).getPower())
        200000 == result.get(199999).getStartTime()
        0.1 > Math.abs(233.33333333 - result.get(299999).getPower())
        0 == result.get(299999).getStartTime()
        0.1 > Math.abs(200 - result.get(399999).getPower())
        0 == result.get(399999).getStartTime()
    }
}
