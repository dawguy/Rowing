package com.roweatrow.server.workouts

import com.roweatrow.server.models.ErgSplit
import com.roweatrow.server.models.Split
import spock.lang.Specification
import spock.lang.Subject

import java.time.Duration

class PowerProfileSegmentTest extends Specification {
    def "Join With Handles Joins With Null Left Side"(){
        given: "A power profile segment"
        PowerProfileSegment powerProfileSegment = new PowerProfileSegment(200,0,10)
        PowerProfileSegment otherPowerProfileSegment = new PowerProfileSegment(100,10,20)
        when: "joinWith is called with null values"
        def result = powerProfileSegment.joinWith(null, otherPowerProfileSegment)
        then:
        30 == result.getDuration()
        133 == result.getPower()
        0 == result.getStartTime()
    }

    def "Join With Handles Joins With Null Right Side"(){
        given: "A power profile segment"
        PowerProfileSegment powerProfileSegment = new PowerProfileSegment(200,0,10)
        PowerProfileSegment otherPowerProfileSegment = new PowerProfileSegment(100,10,20)
        when: "joinWith is called with null values"
        def result = powerProfileSegment.joinWith(otherPowerProfileSegment, null)
        then:
        30 == result.getDuration()
        133 == result.getPower()
        0 == result.getStartTime()
    }

    def "Join With Can Pick After Side"(){
        given: "A power profile segment"
        PowerProfileSegment powerProfileSegment = new PowerProfileSegment(300,10,10)
        PowerProfileSegment lowerPowerProfileSegment = new PowerProfileSegment(100,5,5)
        PowerProfileSegment higherPowerProfileSegment = new PowerProfileSegment(200,20,15)

        when: "joinWith is called with a higher power after"
        def result = powerProfileSegment.joinWith(higherPowerProfileSegment, lowerPowerProfileSegment)
        then:
        25 == result.getDuration()
        240 == result.getPower()
        10 == result.getStartTime()
    }

    def "Join With Can Pick Before Side"(){
        given: "A power profile segment"
        PowerProfileSegment powerProfileSegment = new PowerProfileSegment(300,10,10)
        PowerProfileSegment lowerPowerProfileSegment = new PowerProfileSegment(200,5,5)
        PowerProfileSegment higherPowerProfileSegment = new PowerProfileSegment(100,20,15)

        when: "joinWith is called with a higher power after"
        def result = powerProfileSegment.joinWith(higherPowerProfileSegment, lowerPowerProfileSegment)
        then:
        15 == result.getDuration()
        266 == result.getPower()
        5 == result.getStartTime()
    }

    def "Join With Picks Longer Segment In Case Of A Power Tie With Longer Segment After"(){
        given: "A power profile segment"
        PowerProfileSegment powerProfileSegment = new PowerProfileSegment(300,10,10)
        PowerProfileSegment lowerPowerProfileSegment = new PowerProfileSegment(200,5,5)
        PowerProfileSegment higherPowerProfileSegment = new PowerProfileSegment(200,15,15)

        when: "joinWith is called with equal power averages before and after"
        def result = powerProfileSegment.joinWith(lowerPowerProfileSegment, higherPowerProfileSegment)
        then:
        25 == result.getDuration()
        240 == result.getPower()
        10 == result.getStartTime()
    }


    def "Join With Picks Longer Segment In Case Of A Power Tie With Longer Segment Before"(){
        given: "A power profile segment"
        PowerProfileSegment powerProfileSegment = new PowerProfileSegment(300,25,10)
        PowerProfileSegment lowerPowerProfileSegment = new PowerProfileSegment(200,5,20)
        PowerProfileSegment higherPowerProfileSegment = new PowerProfileSegment(200,30,5)

        when: "joinWith is called with equal power averages before and after"
        def result = powerProfileSegment.joinWith(lowerPowerProfileSegment, higherPowerProfileSegment)
        then:
        30 == result.getDuration()
        233.33333333333334 == result.getPower()
        5 == result.getStartTime()
    }
}
