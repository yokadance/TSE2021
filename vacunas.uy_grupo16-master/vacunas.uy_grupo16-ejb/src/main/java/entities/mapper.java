package entities;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class mapper {



     static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
         ModelMapper modelMapper = new ModelMapper();
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

}