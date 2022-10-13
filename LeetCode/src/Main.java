import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        boolean res = main.isIsomorphic("egg","add");
        System.out.println(res);
    }
    // 724. Find Pivot Index
    // Version1: Each time calculate the sum, leads to algorithm complexity O(n^2)
    public int pivotIndex_old(int[] nums) {
        // pivot itself is not included
        int left, right = 0;

        for(int i=0;i<nums.length;i++){
            left = leftSum(nums, i);
            right = rightSum(nums, i);

            if(left == right){
                return i;
            }

        }
        return -1;
    }
    public int leftSum(int[] nums, int pivot){
        int sum = 0;
        if(pivot ==0){
            return 0;
        }else{
            for(int i=0;i<pivot;i++){
                sum += nums[i];
            }
        }
        return sum;
    }
    public int rightSum(int[] nums, int pivot){
        int sum = 0;
        if(pivot ==nums.length){
            return 0;
        }else{
            for(int i=pivot+1;i<nums.length;i++){
                sum += nums[i];
            }
        }
        return sum;
    }

    // Version2: Reduce unnecessary calculation.
    public int pivotIndex(int[] nums) {
        // pivot itself is not included
        int left = 0, right = 0;

        for(int i=0;i<nums.length;i++){
            right += nums[i];
        }

        for(int i=0;i<nums.length;i++){
            right = right - nums[i];
            if(left == right){
                return i;
            }

            left = left + nums[i];

        }
        return -1;
    }

    // 205. Isomorphic Strings
    // this version is error, as it only considers the frequencies, while the relative position is not inclusive.
    public boolean isIsomorphic_error(String s, String t) {
        // record their frequencies
        int[] sValue = new int[26];
        int[] tValue = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            sValue[index]++;
        }

        for (int j = 0; j < s.length(); j++) {
            int index = t.charAt(j) - 'a';
            tValue[index]++;
        }
        // consider how to compare their frequencies information
        // write info into a List
        List<Integer> sFrequency = new ArrayList<>();
        for (int k = 0; k < 26; k++) {
            if(sValue[k]!=0){
                sFrequency.add(sValue[k]);
            }
        }

        for (int j = 0; j < 26; j++) {
            if(tValue[j]!=0){
                if(sFrequency.contains(tValue[j])){
                    sFrequency.remove(sFrequency.indexOf(tValue[j]));
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isIsomorphic(String s, String t){
        // set up the rules which records the reflection info
        Map<Character, Character> reflection = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            char value = t.charAt(i);
            // one key should have and only have one value
            if(reflection.containsKey(key) && !reflection.get(key).equals(value)){
                return false;
            }else {
                reflection.put(key, value);
            }
        }
        // however, one value should also have and only have one key
        List<Object> validation = new ArrayList<>();
        for (Map.Entry item:
             reflection.entrySet()) {
            if(validation.contains(item.getValue())){
                return false;
            }else {
                validation.add(item.getValue());
            }
        }
        return true;
    }

    // 392. Is Subsequence
    
}