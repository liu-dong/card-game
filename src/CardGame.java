import java.util.*;

/**
 * @author LD
 */
public class CardGame {

    private final static String[] NUMBER = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    //spade：黑桃,heart：红心,club：梅花,diamond：方块,red：大王,black：小王
//    private final static String[] FLOWER_COLOR = {"spade", "heart", "club", "diamond"};
    private final static String[] FLOWER_COLOR = {"黑桃", "红心", "梅花", "方块"};

    public static void main(String[] args) {
        //生成一副牌
        List<Poker> pokerList = getPokerList();
        //洗牌
        Collections.shuffle(pokerList);
        System.out.println("洗过后的牌:" + pokerList.toString());
        //发牌
        Map<String, Object> map = toDeal(pokerList);
        System.out.println("剩余牌的数量：" + pokerList.size());
        //出牌
        play(map, "甲");
        Person jia = (Person) map.get("甲");
        System.out.println(jia.getPokerList().toString());
        play(map, "乙");
        play(map, "丙");
        //如果有炸弹则计分

        //判输赢


    }

    //出牌
    private static void play(Map<String, Object> map, String personName) {
        Scanner input = new Scanner(System.in);
        System.out.println(personName + "请输入要出牌的序号，用逗号隔开");
        String s = input.nextLine();
        System.out.println(personName + "输入数字为：" + s);
        String[] ss = s.split(",");
        Person person = (Person) map.get(personName);
        List<Poker> pokerList = new ArrayList<>(person.getPokerList());
        for (String value : ss) {
            int sort = Integer.parseInt(value);
            //移除需要出的牌
            pokerList.removeIf(poker -> poker.getSort() == sort);
        }
        System.out.println(personName + "剩余的牌" + pokerList.size() + "张：" + pokerList.toString());
        if (pokerList.size() ==0){
            System.out.println(personName+"赢了！");
        }
        person.setPokerList(pokerList);
        map.put(personName, person);
    }


    //生成一副牌
    private static List<Poker> getPokerList() {
        List<Poker> pokerList = new ArrayList<>();
        int flowerColor = 1;
        for (String value : FLOWER_COLOR) {
            int sort = flowerColor++;
            for (String s : NUMBER) {
                Poker poker = new Poker();
                poker.setSort(sort);
                poker.setNumber(s);
                poker.setFlowerColor(value);
                pokerList.add(poker);
                sort += 4;
            }
        }
        Poker bigJoker = new Poker(54, "王", "红色");
        Poker littleJoker = new Poker(53, "王", "黑色");
        pokerList.add(bigJoker);
        pokerList.add(littleJoker);
        System.out.println("数量：" + pokerList.size());
        pokerList = insertSort(pokerList);//排序
        System.out.println("一副新牌共" + pokerList.size() + "张:" + pokerList.toString());
        return pokerList;
    }

    //发牌
    private static Map<String, Object> toDeal(List<Poker> pokerList) {
        Map<String, Object> result = new HashMap<>();
        Person person1 = new Person();
        //排序
        person1.setPokerList(insertSort(pokerList.subList(0, 17)));
        System.out.println("甲的牌" + person1.getPokerList().size() + "张：" + person1.getPokerList().toString());
        result.put("甲", person1);
        Person person2 = new Person();
        person2.setPokerList(insertSort(pokerList.subList(17, 34)));
        System.out.println("乙的牌" + person2.getPokerList().size() + "张：" + person2.getPokerList().toString());
        result.put("乙", person2);
        Person person3 = new Person();
        person3.setPokerList(insertSort(pokerList.subList(34, 51)));
        System.out.println("丙的牌" + person3.getPokerList().size() + "张：" + person3.getPokerList().toString());
        result.put("丙", person3);
        List<Poker> finalCard = insertSort(pokerList.subList(51, 54));
        System.out.println("底牌" + finalCard.size() + "张：" + finalCard.toString());
        result.put("底牌", finalCard);
        return result;
    }

    //插入排序
    public static List<Poker> insertSort(List<Poker> pokerList) {
        Poker[] array = new Poker[pokerList.size()];
        //list转成数组
        for (int i = 0; i < pokerList.size(); i++) {
            array[i] = pokerList.get(i);
        }
        Poker temp;
        int j;
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1].getSort() > array[i].getSort()) {
                temp = array[i];
                j = i;
                while (j > 0 && array[j - 1].getSort() > temp.getSort()) {
                    array[j] = array[j - 1];
                    j--;
                }
                array[j] = temp;
            }
        }
        return Arrays.asList(array);
    }
}
