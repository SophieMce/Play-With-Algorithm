/**
 * 此接口意为让两个参数进行融合
 * @param <E>
 */
public interface Merger<E> {
    E merge(E a, E b);
}
