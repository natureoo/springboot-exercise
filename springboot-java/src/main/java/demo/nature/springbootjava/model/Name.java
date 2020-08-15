package demo.nature.springbootjava.model;

/**
 * @author nature
 * @date 12/8/2020 5:35 下午
 * @email 924943578@qq.com
 */
public  class Name {
    private String first;
    private String last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }
    public String getFirst() {
        return first;
    }
    public String getLast() {
        return last;
    }

}