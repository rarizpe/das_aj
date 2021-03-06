package com.project.utils.converter;

import com.google.common.base.CaseFormat;
import com.google.common.collect.ImmutableMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class ConvertUtils {

    @Autowired
    private ModelMapper modelMapper;

    public<T,K> List<T> convert(Iterable<K> rawData, Class<T> converToClass) {
        List<T> convertedData = new ArrayList<>();

        if(rawData==null)
            return convertedData;

        for(K raw : rawData){
            T convertedElement = modelMapper.map(raw, converToClass);
            convertedData.add(convertedElement);
        }
        return convertedData;
    }

    public<T,K> T convert(K rawData, Class<T> converToClass) {
        if(rawData==null)
            return null;

        return modelMapper.map(rawData, converToClass);
    }

    public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
        return from.stream().map(func).collect(Collectors.toList());
    }

    /**
     * Set values to current destination object from values of destination object
     *
     * @param source the source object
     * @param destination the destination object
     * @param <T> The source Type
     * @param <K> The Destination Type
     */
    public<T,K> void map(K source, T destination) {
        modelMapper.map(source, destination);
    }

    public Map<String,Object> toMap(String k1, Object v1){

        return ImmutableMap.of(k1, v1);
    }
    public Map<String,Object> toMap(String k1, Object v1,String k2, Object v2){
        return ImmutableMap.of(k1, v1,k2,v2);
    }
    public Map<String,Object> toMap(String k1, Object v1,String k2, Object v2,String k3, Object v3){
        return ImmutableMap.of(k1, v1,k2,v2,k3,v3);
    }
    public <T> Map<String,Object> toMap(final Object obj, Class<T> objClass){
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();

        for(Method method : objClass.getDeclaredMethods()){
            if( method.getName().startsWith("get") && !method.getName().startsWith("getClass")
                    && method.getParameterCount() == 0){
                try {
                    final Object value = method.invoke(obj);
                    if(value != null){
                        String key = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, method.getName().substring(3));
                        builder.put(key , value);
                    }
                } catch (Exception e) {
                }
            }
        }

        return builder.build();
    }
    public static ImmutableMap.Builder<String, Object> mapBuilder() {
        return ImmutableMap.builder();
    }

}
