import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAction {
    // 初始化所有人员
    private static List<Person> initPersonList() {
        List<Person> res = new LinkedList<>();
        res.add(new Person("Tom", 18));
        res.add(new Person("Ben", 22));
        res.add(new Person("Jack", 16));
        res.add(new Person("Hope", 4));
        res.add(new Person("Jane", 19));
        res.add(new Person("Hope", 16));
        return res;
    }
    public static List<Person> filter(List<Person> personList) {
        // 过滤出年龄大于18的数据
        return personList.stream().filter(x -> x.getAge() > 18).collect(Collectors.toList());
    }
    public static List<Person> filterAnd(List<Person> personList) {
        // filter 链式调用实现 and
        // 过滤出年龄大于18而且名字以J开头的数据
        return personList.stream().filter(x -> x.getAge() > 18).filter(x -> x.getName().startsWith("J")).collect(Collectors.toList());
    }
    public static List<Person> filterOr(List<Person> personList) {
        // 通过 Predicate 实现 or
        // 过滤出年龄大于18或者名字以J开头的数据
        Predicate<Person> con1 = x -> x.getAge() > 18;
        Predicate<Person> con2 = x -> x.getName().startsWith("J");
        return personList.stream().filter(con1.or(con2)).collect(Collectors.toList());
    }
    public static List<Person> takeWhile(List<Person> personList) {
        // 碰到年级大于20的人停止
        return personList.stream().takeWhile(x -> x.getAge() <= 20).collect(Collectors.toList());
    }

    public static List<Person> dropWhile(List<Person> personList) {
        // 丢弃元素直到碰到年级大于20的人
        return personList.stream().dropWhile(x -> x.getAge() <= 20).collect(Collectors.toList());
    }
    public static List<Person> limit(List<Person> personList) {
        // 取前三个
        return personList.stream().limit(3).collect(Collectors.toList());
    }
    public static List<Person> skip(List<Person> personList) {
        // 跳过前两个
        return personList.stream().skip(2).collect(Collectors.toList());
    }
    public static List<String> map(List<Person> personList) {
        // 获取所有人的名字
        return personList.stream().map(Person::getName).collect(Collectors.toList());
    }
    public static List<String> flatMap(List<Person> personList) {
        // 对每个人的姓名用a做切分
        return personList.stream().flatMap(x -> Arrays.stream(x.getName().split("a"))).collect(Collectors.toList());
    }
    public static int reduce() {
        // 用reduce计算1-5的和
        return Stream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum);
    }
    public static Map<String, Person> collectMap(List<Person> personList) {
        // 映射为Name:Person的Map
        return personList.stream().collect(Collectors.toMap(Person::getName, x -> x, (x, y) -> y));
    }
    public static Map<String, List<Person>> collectGroupBy(List<Person> personList) {
        // 分组为Name:List<Person>的Map
        return personList.stream().collect(Collectors.groupingBy(Person::getName));
    }
    public static String collectJoin(List<Person> personList) {
        // 所有人的名字以逗号分割
        return personList.stream().map(Person::getName).collect(Collectors.joining(",", "{", "}"));
    }
    public static Map<Boolean, List<Person>> collectPartitioningBy(List<Person> personList) {
        // 真假分区
        return personList.stream().collect(Collectors.partitioningBy(i -> i.getAge() > 16));
    }
    public static Boolean anyMatch(List<Person> personList) {
        // 是否有一个叫Hope的人
        return personList.stream().anyMatch(x -> x.getName().equals("Hope"));
    }
    public static Boolean allMatch(List<Person> personList) {
        // 是否都叫Hope
        return personList.stream().allMatch(x -> x.getName().equals("Hope"));
    }
    public static Boolean noneMatch(List<Person> personList) {
        // 是否都不叫Hope
        return personList.stream().noneMatch(x -> x.getName().equals("Hope"));
    }
    public static Person findAny(List<Person> personList) {
        // 找到一个叫Hope的人
        return personList.stream().filter(x -> x.getName().equals("Hope")).findAny().get();
    }
    public static Person findFirst(List<Person> personList) {
        // 找到第一个叫Hope的人
        return personList.stream().filter(x -> x.getName().equals("Hope")).findFirst().get();
    }
    public static void main(String[] args) {
        List<Person> persons = initPersonList();
        System.out.println("原始数据集为\n" + persons);
        System.out.println("过滤出年龄大于18的数据\n" + filter(persons));
        System.out.println("过滤出年龄大于18而且名字以J开头的数据\n" + filterAnd(persons));
        System.out.println("过滤出年龄大于18或者名字以J开头的数据\n" + filterOr(persons));
        System.out.println("碰到年级大于20的人停止\n" + takeWhile(persons));
        System.out.println("丢弃元素直到碰到年级大于20的人\n" + dropWhile(persons));
        System.out.println("取前三个\n" + limit(persons));
        System.out.println("跳过前两个\n" + skip(persons));
        System.out.println("获取所有人的名字\n" + map(persons));
        System.out.println("对每个人的姓名用a做切分\n" + flatMap(persons));
        System.out.println("用reduce计算1-5的和\n" + reduce());
        System.out.println("映射为Name:Person的Map\n" + collectMap(persons));
        System.out.println("分组为Name:List<Person>的Map\n" + collectGroupBy(persons));
        System.out.println("所有人的名字以逗号分割\n" + collectJoin(persons));
        System.out.println("真假分区\n" + collectPartitioningBy(persons));
        System.out.println("是否有一个叫Hope的人\n" + anyMatch(persons));
        System.out.println("是否都叫Hope\n" + allMatch(persons));
        System.out.println("是否都不叫Hope\n" + noneMatch(persons));
        System.out.println("找到一个叫Hope的人\n" + findAny(persons));
        System.out.println("找到第一个叫Hope的人\n" + findFirst(persons));

    }
}