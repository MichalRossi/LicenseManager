package pl.coderslab.coach

import pl.coderslab.season.Season
import pl.coderslab.season.SeasonRepository
import spock.lang.Specification

class CoachTest extends Specification {
    def "Should return last name season from coaches history"() {
        given:
        Coach testCoach = new Coach()
        List<Season> testCoachSeasons = new ArrayList<>()

        Season season = new Season()
        season.setEndYear(2021)
        season.setStartYear(2020)
        testCoachSeasons.add(season)

        testCoach.setSeasons(testCoachSeasons)

        when:
        String result = "2020/2021"

        then:
        result == testCoach.getLastSeason().getName()

    }
}
