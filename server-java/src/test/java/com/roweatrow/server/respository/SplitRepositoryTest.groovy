package com.roweatrow.server.respository

import com.roweatrow.server.models.ErgSplit
import com.roweatrow.server.models.TemplateSplit
import com.roweatrow.server.models.WaterSplit
import spock.lang.Specification
import spock.lang.Subject

class SplitRepositoryTest extends Specification {

    @Subject
    private SplitRepository splitRepository;

    private ErgSplitRepository ergSplitRepository;
    private WaterSplitRepository waterSplitRepository;
    private TemplateSplitRepository templateSplitRepository;

    def setup(){
        ergSplitRepository = Mock()
        waterSplitRepository = Mock(WaterSplitRepository.class)
        templateSplitRepository = Mock()

        splitRepository = new SplitRepository(ergSplitRepository,waterSplitRepository,templateSplitRepository)
    }

    def "Save WaterSplitRepository"() {
        given:
        WaterSplit ww = new WaterSplit()

        when:
        splitRepository.save(ww)

        then:
        1 * waterSplitRepository.save(ww)
    }

    def "Save TemplateSplitRepository"() {
        given:
        TemplateSplit e = new TemplateSplit()

        when:
        splitRepository.save(e)

        then:
        1 * templateSplitRepository.save(e)
    }

    def "Save ergSplit"() {
        given:
        ErgSplit e = new ErgSplit()

        when:
        splitRepository.save(e)

        then:
        1 * ergSplitRepository.save(e)
    }
}
