package pl.coderslab.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ClubConverter implements Converter<String, Club> {

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public Club convert(String s) {
        return clubRepository.findClubById(Long.parseLong(s));
    }

}