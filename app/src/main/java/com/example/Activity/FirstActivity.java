/*
 * ************************************************************
 * 文件：MainActivity.java
 * 模块：app
 * 项目：MyApplication
 * 当前修改时间：2020年11月30日 19:56:37
 * 上次修改时间：2020年11月30日 19:56:37
 * 作者：Havi
 * Copyright (c) 2020
 * ************************************************************
 *
 */

package com.example.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.telephony.mbms.FileInfo;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Activity.BroadCast.NetworkBroadcastManager;
import com.example.Activity.Fruit.RecyclerViewActivity;
import com.example.Activity.Message.MessageActivity;
import com.example.Activity.fragment.FragmentContainer;
import com.example.Activity.强制下线.LoginActivity;
import com.example.Activity.文件保存.FilePersistenceActivity;
import com.example.mj.ArrayList.List;
import com.example.mj.Map.HashMap;
import com.example.mj.Map.Map;
import com.example.mj.Map.Model.Key;
import com.example.mj.Map.Model.SubKey1;
import com.example.mj.Map.Model.SubKey2;
import com.example.mj.Map.TreeMap;
import com.example.mj.Set.ListSet;
import com.example.mj.Set.Set;
import com.example.mj.Set.TreeSet;
import com.example.mj.Set.TreeSet1;
import com.example.mj.二叉堆.BinaryHeap;
import com.example.mj.二叉树.AVLTree;
import com.example.mj.二叉树.BST;
import com.example.mj.BinarySearchTree.Person;
import com.example.mj.LinkedList.circleList.CircleLinkedList;
import com.example.mj.Queue.CircleDeque;
import com.example.mj.Queue.CircleQueue;
import com.example.mj.Queue.Queue;
import com.example.mj.printer.BinaryTrees;
import com.example.mj.stack.Stack1;
import com.example.mj.二叉树.BinaryTree;
import com.example.mj.二叉树.RBTree;
import com.example.tool.Assert;
import com.example.tool.Files;
//import com.example.mj.HashTable.Person;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Stack;
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class FirstActivity extends AppCompatActivity {

    private final static String TAG = "FirstActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        //获取活动有没有存储数据
        if (savedInstanceState != null) {
            //活动销毁时保存了数据
            String data = savedInstanceState.getString("temp_data");
            Log.d(TAG,data);
        }
//      --------------------------------------------------------
        int[] arr = new int[] {5,2,4, 1, 9};
        System.out.println("-----------------------");
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(FirstActivity.this, "you click the button", Toast.LENGTH_SHORT).show();
                /*
                显示调用Activity
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
                 */

                /*
                隐式调用Intent触发其他程序的活动
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.baidu.com"));
                startActivity(intent);
                 */

                /*
                发起电话调用
                 */
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10086"));
//                startActivity(intent);

                /*
                向下一个activity传递数据
                 */
//                String data = "hello Havi Lee";
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                intent.putExtra("extra_data", data);
////                startActivity(intent);
//                //进行反向传值
//                startActivityForResult(intent,1);
                //新的写法
                SecondActivity.actionStart(FirstActivity.this,"data1");


            }
        });

        Button normal = findViewById(R.id.normal);

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });

        Button dialog = findViewById(R.id.dialog);
        dialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });

        Button singleTap = findViewById(R.id.singleTap);
        singleTap.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SingleTapActivity.class);
                startActivity(intent);
            }
        });

        Button textButton = findViewById(R.id.textview);
        textButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, TextViewActivity.class);
                startActivity(intent);
            }
        });

        Button listButton = findViewById(R.id.listview);
        listButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        Button recycler = findViewById(R.id.recycler_view);
        recycler.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        Button ninePatch = findViewById(R.id.nine_patch);
        ninePatch.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, NinePatch.class);
                startActivity(intent);
            }
        });

        Button message = findViewById(R.id.message_view);
        message.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });

        Button fragment = findViewById(R.id.fragment_view);
        fragment.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, FragmentContainer.class);
                startActivity(intent);
            }
        });

        Button network = findViewById(R.id.network_change);
        network.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, NetworkBroadcastManager.class);
                startActivity(intent);
            }
        });

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button filePersistence = findViewById(R.id.file_persistence);
        filePersistence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, FilePersistenceActivity.class);
                startActivity(intent);
            }
        });

        Button showPerference = findViewById(R.id.perference);
        showPerference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, PermissionActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
    这个是活动反向传值实例代码
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returndata = data.getStringExtra("data_return");
                    Log.d("TAG", "onActivityResult:" + returndata.toString());
                }
                break;
            default:

        }
    }

    /*
    为了在活动进入后台之后被销毁，需要将当前活动的状态保存；
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        /*
        类似iOS的Default
         */
        String tempData = "临时数据";
        outState.putString("temp_data", tempData);
    }

    /*
         系统的方法
        */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(FirstActivity.this, "add item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this, "remove item", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStart() {
        super.onStart();
//        testList(new ArrayList<Integer>());
//        testList(new CircleLinkedList<Integer>());
//        testQueue();
//        testCircleQueue();
//        testCircleDeque();
//        testBinarySearchTree();
//        testIntegerBST();
//        testAVLTree();
//        testRBTree1();
//        testListSet();
//        testBinarySet();
//        testTreeMap();
//        testHashCode();
//        test2(new HashMap<Object, Integer>());
//        test3(new HashMap<Object, Integer>());
//        test4(new HashMap<Object, Integer>());
//        test5(new HashMap<Object, Integer>());
        testHeap();
        Log.d(TAG, "onStart: ");
    }

    static void testHeap() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);

    }

    static void testHeap1() {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

    }

//    static void test1() {
//        String filepath = "C:\\Users\\MJ Lee\\Desktop\\src\\java\\util\\concurrent";
//        FileInfo fileInfo = Files.read(filepath, null);
//        String[] words = fileInfo.words();
//
//        System.out.println("总行数：" + fileInfo.getLines());
//        System.out.println("单词总数：" + words.length);
//        System.out.println("-------------------------------------");
//
//        test1Map(new TreeMap<>(), words);
//        test1Map(new HashMap<>(), words);
//        test1Map(new LinkedHashMap<>(), words);
//    }
//
//    static void test1Map(Map<String, Integer> map, String[] words) {
//        Times.test(map.getClass().getName(), new Task() {
//            @Override
//            public void execute() {
//                for (String word : words) {
//                    Integer count = map.get(word);
//                    count = count == null ? 0 : count;
//                    map.put(word, count + 1);
//                }
//                System.out.println(map.size()); // 17188
//
//                int count = 0;
//                for (String word : words) {
//                    Integer i = map.get(word);
//                    count += i == null ? 0 : i;
//                    map.remove(word);
//                }
//                Asserts.test(count == words.length);
//                Asserts.test(map.size() == 0);
//            }
//        });
//    }

    static void test2(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            map.put(new Key(i), i + 5);
        }
        Assert.test(map.size() == 20);
        Assert.test(map.get(new Key(4)) == 4);
        Assert.test(map.get(new Key(5)) == 10);
        Assert.test(map.get(new Key(6)) == 11);
        Assert.test(map.get(new Key(7)) == 12);
        Assert.test(map.get(new Key(8)) == 8);
    }

    static void test3(HashMap<Object, Integer> map) {
        map.put(null, 1); // 1
        map.put(new Object(), 2); // 2
        map.put("jack", 3); // 3
        map.put(10, 4); // 4
        map.put(new Object(), 5); // 5
        map.put("jack", 6);
        map.put(10, 7);
        map.put(null, 8);
        map.put(10, null);
        Assert.test(map.size() == 5);
        Assert.test(map.get(null) == 8);
        Assert.test(map.get("jack") == 6);
        Assert.test(map.get(10) == null);
        Assert.test(map.get(new Object()) == null);
        Assert.test(map.containsKey(10));
        Assert.test(map.containsKey(null));
        Assert.test(map.containsValue(null));
        Assert.test(map.containsValue(1) == false);
    }

    static void test4(HashMap<Object, Integer> map) {
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        map.remove("jack");
        map.remove("jim");
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            Assert.test(map.remove(new Key(i)) == i);
        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
        Assert.test(map.size() == 19);
        Assert.test(map.get(new Key(1)) == 6);
        Assert.test(map.get(new Key(2)) == 7);
        Assert.test(map.get(new Key(3)) == 8);
        Assert.test(map.get(new Key(4)) == 4);
        Assert.test(map.get(new Key(5)) == null);
        Assert.test(map.get(new Key(6)) == null);
        Assert.test(map.get(new Key(7)) == null);
        Assert.test(map.get(new Key(8)) == 8);
        map.traversal(new Map.Visitor<Object, Integer>() {
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test5(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        Assert.test(map.get(new SubKey1(1)) == 5);
        Assert.test(map.get(new SubKey2(1)) == 5);
        Assert.test(map.size() == 20);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");

    }

    private void testTreeMap() {
        Map<String, Integer> map = new TreeMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("b", 5);
        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            protected boolean visit(String key, Integer value) {
                System.out.println("key:" + key + "   value:" + value);
                return false;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void testHashCode() {
        Map<Object, Object> map = new HashMap<>();
        com.example.mj.HashTable.Person person1 = new com.example.mj.HashTable.Person(10, 11.0f, "havi");
        com.example.mj.HashTable.Person person2 = new com.example.mj.HashTable.Person(10, 11.0f, "havi");;
        map.put(person1, "abc");
        map.put(person2, "ccc");
        map.put("jack","ddd");
        map.put("rose","eee");
        map.put("jack", "fff");
        map.put(null, "ggg");
//        System.out.println("map size:"+map.size());
//        System.out.println("jack:" + map.get("jack"));
//        System.out.println("rose:" + map.get("rose"));
//        System.out.println("null:" + map.get(null));
//        System.out.println(map.remove("rose"));
//        System.out.println("map size:"+map.size());
//        map.traversal(new Map.Visitor<Object, Object>() {
//            @Override
//            protected boolean visit(Object key, Object value) {
//                System.out.println(key + ":   " + value);
//                return false;
//            }
//        });
//        System.out.println(map.containsKey(person1));
//        System.out.println(map.containsValue("fff"));
        HashMap<Object, Integer> map1 = new HashMap<>();
        for (int i = 0; i <= 19; i++) {
            map1.put(new Key(i), i);
        }
        map1.put(new Key(4), 100);
        Assert.test(map1.get(new Key(4)) == 100);
        map1.print();

    }

    private void calculateStringHashCode() {
        String string = "jack";
        int len = string.length();
        int hashCode = 0;
        for (int i = 0; i < len; i++) {
            char c = string.charAt(i);
//            hashCode = hashCode * 31 + c;
            hashCode = (hashCode << 5) - hashCode + c;
        }
    }

    private void calculateHashCode() {
        Integer a = 100;
        Float b = 10.6f;
        Long c = 156l;
        Double d = 10.0;
        String e = "rose";
        System.out.println(a.hashCode());
        b.hashCode();
        c.hashCode();
        d.hashCode();
        e.hashCode();
        Person person = new Person("jack", 10);
        System.out.println(person.hashCode());
    }

    private void testBinarySet() {
        Set<Integer> set = new TreeSet<>();
        set.add(4);
        set.add(3);
        set.add(4);
        set.add(1);
        set.add(7);
        set.traversal(new BinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    private void testListSet() {
        Set<Integer> set = new ListSet<>();
        set.add(1);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(7);
        set.traversal(new BinaryTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    private void  testRBTree1() {
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rbTree.add(data[i]);
        }
        BinaryTrees.println(rbTree);
        for (int i = 0; i < data.length; i++) {
            rbTree.remove(data[i]);
            BinaryTrees.println(rbTree);
        }

    }

    private void  testRBTree() {
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rbTree.add(data[i]);
        }
        BinaryTrees.println(rbTree);

     }

    private void testIntegerBST() {
        Integer data[] = new Integer[] {
                7,4,9,2,5,8,11,3
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
        bst.remove(3);
        bst.remove(11);
        BinaryTrees.println(bst);
    }

    private void testAVLTree() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        avlTree.add(4);
        avlTree.remove(1);
        BinaryTrees.println(avlTree);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void testBinarySearchTree() {


//        BST<Person> bst2 = new BST<>();
//        //匿名类
        BST<Person> bst3 = new BST<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }

        });
        Integer data[] = new Integer[] {
                7,4,9,2,5,8,11,3
        };
        for (int i = 0; i < data.length; i++) {
            bst3.add(new Person(i+"",data[i]));
        }
//        BinaryTrees.println(bst3);
//        bst3.levelOrderTraversal(new BST.Visitor<Person>() {
//            @Override
//            public boolean visit(Person element) {
//                System.out.println("_" + element);
//                return false;
//            }
//        });

        System.out.println(bst3.toString());
        System.out.println("层树"+bst3.height());

//        BST<Person> bst1 = new BST<>(new PersonComparator());

    }

    private static class PersonComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getAge() - o2.getAge();
        }
    }

    private void testCircleDeque() {
        CircleDeque<Integer> circleQueue = new CircleDeque<>();
        for (int i=0;i<10;i++) {
            circleQueue.enQueueFront(i+1);
            circleQueue.enQueueRear(i+100);
        }

        for (int i = 0; i < 3; i++) {
            circleQueue.deQueueFront();
            circleQueue.deQueueRear();
        }

        circleQueue.enQueueFront(11);
        circleQueue.enQueueFront(12);

        System.out.println(circleQueue.toString());
//        while (!circleQueue.isEmpty()) {
//            System.out.println(circleQueue.deQueue());
//        }
    }

    private void testCircleQueue() {
        CircleQueue<Integer> circleQueue = new CircleQueue<>();
        for (int i=0;i<10;i++) {
            circleQueue.enQueue(i);
        }

        for (int i = 0; i < 5; i++) {
            circleQueue.deQueue();
        }

        for (int i = 15; i < 23; i++) {
            circleQueue.enQueue(i);
        }
        System.out.println(circleQueue.toString());
//        while (!circleQueue.isEmpty()) {
//            System.out.println(circleQueue.deQueue());
//        }
    }

    private void testStack() {
        Stack1<Integer> stack1 = new Stack1<Integer>();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        Stack<Integer> list2 = new Stack<Integer>();

    }

    static void josephus() {
        CircleLinkedList<Integer> list = new CircleLinkedList<>();
        for (int i = 0; i <= 8; i++) {
            list.add(i);
        }
        //让环指向头结点
        list.reset();

        while (list.isEmpty()) {
            list.next();
            list.next();
            System.out.println(list.remove());
        }
    }

    private void testQueue() {
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }

    private void testList(List<Integer> list) {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55);
        list.add(2, 66);
        list.add(list.size(), 77);

        list.remove(0);
        list.remove(2);
        list.remove(list.size()-1);
//
        Assert.test(list.indexOf(44) == 3);
        Assert.test(list.indexOf(22) == List.ELEMENT_NOT_FOUND);
        Assert.test(list.contains(33));
        Assert.test(list.get(0) == 11);
        Assert.test(list.get(1) == 66);
        Assert.test(list.get(list.size()-1) == 44);
        System.out.println(list.toString());

    }
}



