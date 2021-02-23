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
        round(map, "甲");
        round(map, "乙");
        round(map, "丙");
    }

    /**
     * 回合
     * @param map
     * @param personName
     */
    private static void round(Map<String, Object> map, String personName) {
        String[] ss = getMovePoker(personName);
        //校验规则
        Person person = (Person) map.get(personName);//出牌人
        List<Poker> pokerList = play(ss, person);//出牌
        //如果有炸弹则计分
        int score = computerScore(ss);
        //判输赢
        if (pokerList.size() == 0) {
            System.out.println(personName + "赢了！");
        }
        person.setPokerList(pokerList);
        person.setScore(score);
        map.put(personName, person);
    }

    /**
     * 出牌
     * @param ss
     * @param person
     * @return
     */
    private static List<Poker> play(String[] ss, Person person) {
        List<Poker> pokerList = new ArrayList<>(person.getPokerList());
        for (String value : ss) {
            int sort = Integer.parseInt(value);
            //移除需要出的牌
            pokerList.removeIf(poker -> poker.getSort() == sort);
        }
        System.out.println(person.getPersonName() + "剩余的牌" + pokerList.size() + "张：" + pokerList.toString());
        return pokerList;
    }

    /**
     * 获取每个人需要出的牌
     * @param personName
     * @return
     */
    private static String[] getMovePoker(String personName) {
        Scanner input = new Scanner(System.in);
        System.out.println(personName + "请输入要出牌的序号，用逗号隔开");
        String s = input.nextLine();
        System.out.println(personName + "输入数字为：" + s);
        return s.split(",");
    }

    /**
     * 计分
     * @param ss
     * @return
     */
    private static int computerScore(String[] ss) {
        int num = 0;//炸弹的数量
        int score = 100;//分数
        //判断炸弹计分并计分
        for (int i = 0; i < ss.length; i++) {
            if ((ss[i] + 1).equals(ss[i + 1])) {
                num++;
                //王炸
                if (Integer.parseInt(ss[i]) == 53 && Integer.parseInt(ss[i + 1]) == 54) {
                    score = score * 4;
                }
            }
        }
        if (num >= 4) {
            score *= 2;
        }
        return score;
    }


    /**
     * 生成一副牌
     * @return
     */
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

    /**
     * 发牌
     * @param pokerList
     * @return
     */
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

    /**
     * 插入排序
     * @param pokerList
     * @return
     */
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
