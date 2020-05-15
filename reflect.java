class Student {
    private String name;
    private String gender;
    private int age;

    public Student() {
        System.out.println("Student()");
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Student(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public void setAge(int age){
        this.age=age;
    }
    private void setName(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}

public class Test {
    public static void main(String[] args) throws Exception {
        Student student=new Student();

        //拿到类对象
        //方法一：需要知道全路径名
        Class<?> stu=Class.forName("Student");
        //方法二：类的静态方法
        Class<?> s=Student.class;
        //方法三：对象的静态方法
        Class<?> t=student.getClass();

        //反射构造器

        //拿到指定构造方法(非私有)，参数一定要匹配
        Constructor<?> stuCon1=stu.getConstructor(String.class,int.class);
        System.out.println(stuCon1);

        //拿到所有非私有构造方法，用数组来接收
        Constructor<?>[] stuCon2=stu.getConstructors();
        System.out.println(stuCon2.length);

        //拿到声明的构造方法
        Constructor<?> stuCon3=stu.getDeclaredConstructor(String.class,String.class,int.class);
        System.out.println(stuCon3);

        //拿到所有声明的构造方法(包括私有)
        Constructor<?>[] stuCon4=stu.getDeclaredConstructors();
        System.out.println(stuCon4.length);




        //创造对象实例
        //非私有构造方法构造
        Constructor<?> t1=stu.getConstructor(String.class,int.class);
        Student inst1=(Student)t1.newInstance("petrt",19);
        System.out.println(inst1);

        //私有构造方法构造(需要设置一下访问权限)
        Constructor<?> t2=stu.getDeclaredConstructor(String.class,String.class,int.class);
        t2.setAccessible(true);
        Student inst2=(Student)t2.newInstance("zhangsan","男",20);
        System.out.println(inst2);



        //反射属性

        //拿到非私有属性
        Field[] f1=stu.getFields();

        //拿到所有声明属性
        Field[] f2=stu.getDeclaredFields();

        //拿到私有属性
        Field name=stu.getDeclaredField("name");
        name.setAccessible(true);
        name.set(inst2,"lisi");
        System.out.println(name.get(inst2));
        System.out.println(inst2);

        Field age=stu.getDeclaredField("age");
        age.setAccessible(true);
        age.set(inst2,23);
        System.out.println(age.get(inst2));
        System.out.println(inst2);



        //反射方法
        //获取所有本类声明方法
        Method[] m1=stu.getDeclaredMethods();
        System.out.println(m1.length);
        System.out.println(Arrays.toString(m1));

        //获取所有非私有方法(包括继承基类的public方法)
        Method[] m2=stu.getMethods();
        System.out.println(m2.length);
        System.out.println(Arrays.toString(m2));

        //拿到私有方法
        Method setname=stu.getDeclaredMethod("setName",String.class);
        setname.setAccessible(true);
        setname.invoke(inst2,"wangwu");
        System.out.println(inst2);


        Method getname=stu.getDeclaredMethod("getName");
        getname.setAccessible(true);
        System.out.println(getname.invoke(inst2));

        //拿到公有方法
        Method setage=stu.getDeclaredMethod("setAge",int.class);
        setage.invoke(inst2,25);
        System.out.println(inst2);

    }

}

