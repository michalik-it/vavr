package it.michalik

import spock.lang.Specification

class DummyClassSpec extends Specification {
    def subject = new DummyClass()

    def "test sayDummy"() {
        when:
            def result = subject.sayDummy()

        then:
            result == "dummy"
    }
}
