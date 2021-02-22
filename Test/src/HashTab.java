package com.datastructure.HashTableDemo;

import java.util.Scanner;

public class HashTab {

    public static void main(String[] args) {

        HashT hashT = new HashT(10);

        boolean flag = true;
        while (flag) {
            System.out.println("1.add.添加元素");
            System.out.println("2.del.删除元素");
            System.out.println("3.find.查找元素");
            System.out.println("4.list.展示元素");
            System.out.println("5.exit.退出");
            System.out.println("6.update.修改");
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入您的选择：");
            String key = scanner.next();
            int id;
            int age;
            String name;
            switch (key) {
                case "1":
                    System.out.println("请输入ID：");
                    id = scanner.nextInt();
                    System.out.println("请输入年龄：");
                    age = scanner.nextInt();
                    System.out.println("请输入姓名：");
                    name = scanner.next();
                    Emp emp = new Emp(id, age, name);
                    hashT.add(emp);
                    break;

                case "2":
                    System.out.println("请输入ID：");
                    id = scanner.nextInt();
                    hashT.del(id);
                    break;

                case "3":
                    System.out.println("请输入ID：");
                    id = scanner.nextInt();
                    hashT.find(id);
                    break;

                case "4":
                    hashT.show();
                    break;

                case "5":
                    scanner.close();
                    flag = false;
                    break;

                case "6":
                    System.out.println("请输入要修改的ID：");
                    id = scanner.nextInt();
                    System.out.println("请输入年龄：");
                    age = scanner.nextInt();
                    System.out.println("请输入姓名：");
                    name = scanner.next();
                    hashT.update(id, age, name);
                    break;

                default:
                    break;
            }
        }
        System.out.println("退出成功");
    }
}

//建立哈希表
class HashT {
    private CreateLinkedList[] arr;
    private int size;

    protected HashT(int size) {
        this.size = size;
        arr = new CreateLinkedList[size];

        //将哈希表中的每一个链表初始化
        for (int i = 0; i < size; i++) {
            arr[i] = new CreateLinkedList();
        }
    }

    //散列函数,用于确定数据放在哪一个链表里
    public int Hash(int id) {
        return id % size;
    }

    //添加
    public void add(Emp emp) {
        int position = Hash(emp.getId());
        arr[position].add(emp);
    }

    //删除
    public void del(int id) {
        int position = Hash(id);
        arr[position].del(id);
    }

    //展示
    public void show() {
        for (int i = 0; i < size; i++) {
            arr[i].show(i);
        }
    }

    //查找
    public void find(int id) {
        int position = Hash(id);
        System.out.println(arr[position].search(id));
    }

    //修改
    public Emp update(int id, int age, String name) {
        int position = Hash(id);
        Emp search = arr[position].search(id);
        search.setAge(age);
        search.setName(name);
        return search;
    }

}

//建立链表
class CreateLinkedList {

    //指定链表的头,默认为空
    private Emp head;

    //添加,添加到链表的尾部
    public void add(Emp emp) {
        //如果是第一个元素
        if (head == null) {
            head = emp;
        } else {
            //建立一个辅助指针
            Emp temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            //已经找到链表的尾部，添加元素
            temp.setNext(emp);
        }
    }


    //删除
    public void del(int id) {

        if (head.getNext() == null && head.getId() == id) {
            head = null;
        } else if (head.getNext() != null && head.getId() == id) {
            head = head.getNext();
        } else {
            //建立一个辅助指针
            Emp temp = head;

            while (temp.getNext() != null && temp.getNext().getId() != id) {
                temp = temp.getNext();
            }
            if (temp.getNext() != null) {
                //已经找到要删除元素的前一个元素
                temp.setNext(temp.getNext().getNext());
            } else {
                System.out.println("哈希表中没有此元素");
            }
        }
    }

    //查找
    public Emp search(int id) {
        //建立一个辅助指针
        Emp temp = head;
        int count = 0;
        while (temp != null && temp.getId() != id) {
            temp = temp.getNext();
            count++;
        }
        if (temp == null) {
            return null;
        } else {
            System.out.println(count);
            return temp;
        }
    }

    //展示
    public void show(int num) {
        if (head == null) {
            System.out.println("第" + (num + 1) + "条链表没有数据");
            return;
        }
        System.out.printf("第" + (num + 1) + "条链表输出数据为");
        //建立一个辅助指针
        Emp temp = head;
        while (temp != null) {
            System.out.printf("==>id=%d,age=%d,name=%s\t", temp.getId(), temp.getAge(), temp.getName());
            temp = temp.getNext();
        }
        System.out.println();
    }
}


//建立员工节点
class Emp {

    private int id;
    private int age;
    private String name;
    private Emp next;

    public Emp(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

}

