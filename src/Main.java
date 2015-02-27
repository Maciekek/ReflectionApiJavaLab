import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        long time = 0;
        Person p = new Person();

        for (int i = 0; i < 10000; i++) {
            time += setNameByNormalWay();
//            System.out.println(time);
        }
        System.out.println("Set Name By Normal Way TIME: " + time);

        time = 0;
        for (int i = 0; i < 10000; i++) {
            time += setNameByReflection();
//           System.out.println(time);

        }
        System.out.println("Set Name By Reflection Way TIME: " + time);

        time = 0;
        for (int i = 0; i < 10000; i++) {
            time += getNameByNormalWay();
//           System.out.println(time);

        }
        System.out.println("Get Name By Normal Way TIME: " + time);

        time = 0;
        for (int i = 0; i < 10000; i++) {
            time += getNameByReflection();
//           System.out.println(time);
        }
        System.out.println("Get Name By Reflection Way TIME: " + time);

        time = 0;
        for (int i = 0; i < 10000; i++) {
            long startTime = System.nanoTime();
            p.testMethod("Maciej");
            long stopTime = System.nanoTime();
            time += stopTime - startTime;
        }
        System.out.println("Call test methon in normal way, time: " + time);

        time = 0;
        for (int i = 0; i < 10000; i++) {
            long startTime = System.nanoTime();
            Method method = p.getClass().getMethod("testMethod", String.class);
            method.invoke(p, "maciej");

            long stopTime = System.nanoTime();
            time += stopTime - startTime;
        }
        System.out.println("Call test method in reflection way, time: " + time);
    }

    private static long getNameByReflection() throws NoSuchFieldException, IllegalAccessException {
//        System.out.println("Get name by reflection ----");
        Person p = new Person("Maciej", 21);
        long startTime = System.nanoTime();
        Field f = p.getClass().getDeclaredField("name");
        long stopTime = System.nanoTime();
//        System.out.println(f.get(p));

        return stopTime - startTime;
    }

    private static long getNameByNormalWay() {
        Person p = new Person("Maciej", 21);
        long startTime = System.nanoTime();
        String name = p.getName();
        long stopTime = System.nanoTime();
//        System.out.println(stopTime-startTime);

        return stopTime - startTime;
    }


    public static long setNameByReflection() throws NoSuchFieldException, IllegalAccessException {
        Person p2 = new Person();
        long startTime = System.nanoTime();
        Field f = p2.getClass().getDeclaredField("age");
        f.setAccessible(true);
        f.set(p2, 200);
        long stopTime = System.nanoTime();

//        System.out.println(f.get(p2));
//        System.out.println(stopTime-startTime);

        return stopTime - startTime;

    }

    public static long setNameByNormalWay() {
        Person p1 = new Person();
        long startTime = System.nanoTime();
        p1.setName("Maciej");
        long stopTime = System.nanoTime();

        long timeOfSet = (stopTime - startTime);

//        System.out.println(p1.getName());
//        System.out.println(timeOfSet);

        return stopTime - startTime;

    }


}
