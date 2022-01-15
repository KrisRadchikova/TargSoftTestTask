package by.targsoft.test.targsofttest.service.interfaces;

public interface Mapper<T, K> {

    K toDto(T entity);

    T toEntity(K dto);
}
