package lai_online;

import java.util.*;


public class Class24 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test2();
//		test3();
//		test5();
//		test5_1();
//		test6();
//		test7();
		test9();
	}
	
	/*
	 * task1
	 * 2 Sum Easy Data Structure
	 * Determine if there exist two elements in a given array, the sum of which is the given target number.
	 * Assumptions
	 * The given array is not null and has length of at least 2
	 * ​Examples
	 * A = {1, 2, 3, 4}, target = 5, return true (1 + 4 = 5)
	 * A = {2, 4, 2, 1}, target = 4, return true (2 + 2 = 4)
	 * A = {2, 4, 1}, target = 4, return false
	 */
	public boolean existSum(int[] array, int target) {
		// write your solution here
		if (array == null || array.length == 0) {
			return false;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(array[0]);

		for (int i = 1; i < array.length; i++) {
			if (set.contains(target - array[i])) {
				return true;
			} else {
				set.add(array[i]);
			}
		}
		return false;
	}
	
	/*
	 * task2
	 * 2 Sum All Pair I
	 * Fair Data Structure
	 * Find all pairs of elements in a given array that sum to the given target number. 
	 * Return all the pairs of indices.
	 * Assumptions
	 * The given array is not null and has length of at least 2.
	 * Examples
	 * A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
	 * A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
	 * 
	 * Pair(val, index). Then sort the pairs according to val
	 * two pointers. leftBound, rightBount. 
	 */
	public static class Item {
		public int val;
		public int index;
		public Item(int v, int index) {
			this.val = v;
			this.index = index;
		}
	}
	public static void test2() {
		int[] array = {3, 5, 3, 2, 4, 4};
		int target = 8;
		List<List<Integer>> result = task2_1_allPairs(array, target);
		System.out.println(result);
		
	}
	
	// this donesn't work well in Lai code
	public static List<List<Integer>> task2_allPairs(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (array == null || array.length == 0) {
			return result;
		}
		ArrayList<Item> temp = new ArrayList<Item>();
		for(int i = 0; i < array.length; i ++) {
			temp.add(new Item(array[i], i));
		}
		Comparator<Item> myComp = new Comparator<Item>() {
			
			@Override
			public int compare(Item o1, Item o2) {
				// TODO Auto-generated method stub
				return o1.val - o2.val;
			}
		};
		Collections.sort(temp, myComp);
		
		// debug
		for(Item item: temp) {
			System.out.print(item.val + " ");
		}
		
		int i = 0, j = temp.size() - 1;
		
		while(i < j) {
			int curSum = temp.get(i).val + temp.get(j).val;
			if (curSum == target) {
				List<Integer> curList = new ArrayList<Integer>();
				curList.add(Math.min(temp.get(i).index, temp.get(j).index));
				curList.add(Math.max(temp.get(i).index, temp.get(j).index));
				result.add(curList);
				
				i ++;
				j --;
			} else if (curSum < target) {
				i ++;
			} else {
				j --;
			}
		}
		return result;
	}
	
	
	// this works OK in lai code
	public static List<List<Integer>> task2_1_allPairs(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (array == null || array.length == 0) {
			return result;
		}
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> arrayNoDup = new ArrayList<Integer>();
		// put every element into map
		for(int i = 0; i < array.length; i ++) {
			ArrayList<Integer> curElemIndexList =null;
			int curElem = array[i];
			if (!map.containsKey(curElem)) {
				curElemIndexList = new ArrayList<Integer>();
				map.put(curElem, curElemIndexList);
			} else {
				curElemIndexList = map.get(curElem);
			}
			curElemIndexList.add(i);
		}
		for(Map.Entry<Integer, ArrayList<Integer>> entry: map.entrySet()) {
			arrayNoDup.add((Integer) entry.getKey());
		}
		Collections.sort(arrayNoDup);
		
//		System.out.println("print out hash Map");
//		for(Map.Entry<Integer, ArrayList<Integer>> entry: map.entrySet()) {
//			System.out.print(entry.getKey() +"  ");
//			System.out.println(entry.getValue());
//		}
//		System.out.println("finish print hash map");
//		
//		System.out.println("arrayNoDup : ");
//		System.out.println(arrayNoDup);
		
		for(int it = 0; it < arrayNoDup.size(); it ++) {
			int curKey = arrayNoDup.get(it);
			int targetKey = target - curKey;
//			System.out.println("--------------------");
//			System.out.println("curKey = " +  curKey);
//			System.out.println("targetKey = " + targetKey);
//			System.out.println("====================");
			
			if (map.containsKey(targetKey)) {
				if (curKey != targetKey) {
					for(Integer i: map.get(curKey)) {
						for(Integer j: map.get(targetKey)) {
							ArrayList<Integer> curResult = new ArrayList<Integer>();
							curResult.add(Math.min(i, j));
							curResult.add(Math.max(i, j));
							result.add(curResult);
						}
					}
//					System.out.println("~~~~~~~");
//					System.out.println(result);
//					System.out.println("^^^^^^^");
					
					map.remove(curKey);
				} else {
					// curKey == targetKey
					ArrayList<Integer> curList = map.get(curKey);
					System.out.println(curList);
					if (curList.size() != 1) {
						for(int i = 0; i < curList.size(); i ++) {
							for(int j = i + 1; j < curList.size(); j ++) {
								ArrayList<Integer> curResult = new ArrayList<Integer>();
								curResult.add(Math.min(curList.get(i), curList.get(j)));
								curResult.add(Math.max(curList.get(i), curList.get(j)));
								result.add(curResult);
							}
						}
					}
					
					//Debug
//					System.out.println("&&&&&&&");
//					System.out.println(result);
//					System.out.println("#######");
					map.remove(curKey);
				}
			}
		}
		return result;
	}
	
	
	
	
	
	/*
	 * task3
	 * 2 Sum All Pair II
	 * Fair Data Structure
	 * Find all pairs of elements in a given array that sum to the pair the given target number. 
	 * Return all the distinct pairs of values.
	 * Assumptions
	 * The given array is not null and has length of at least 2
	 * The order of the values in the pair does not matter
	 * Examples
	 * A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]
	 */
	
	public static void test3() {
		int[] array = {2, 1, 3, 2, 4, 3, 4, 2};
		int target = 6;
		List<List<Integer>> result = task3_allPairs(array, target);
		System.out.println(result);
		
	}
	
	public static List<List<Integer>> task3_allPairs(int[] array, int target) {
		// write your solution here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (array == null || array.length == 0) {
			return result;
		}
		Arrays.sort(array);
		int i = 0, j = array.length - 1;
		while(i < j) {
//			while(i + 1 < j && array[i+1] == array[i]) {
//				i ++;
//			}
			int curSum = array[i] + array[j];
			if (curSum == target) {
				List<Integer> element = new ArrayList<Integer>();
				element.add(array[i]);
				element.add(array[j]);
				result.add(element);
				while(i + 1 < j && array[i + 1]== array[i]) {
					i ++;
				}
				while(i  < j - 1 && array[j - 1] == array[j]) {
					j --;
				}
				i ++; 
				j --;
			}  else if (curSum < target) {
				while(i + 1 < j && array[i + 1]== array[i]) {
					i ++;
				}
				i ++;
			} else {
				while(i  < j - 1 && array[j - 1] == array[j]) {
					j --;
				}
				j --;
			}
		}
		return result;
	}
	
	/*
	 * task4
	 * 2 Sum Closest
	 * Fair Data Structure
	 * Find the pair of elements in a given array that sum to a value that is closest to the given target number.
	 * Return the values of the two numbers.
	 * Assumptions
	 * The given array is not null and has length of at least 2
	 * Examples
	 * A = {1, 4, 7, 13}, target = 7, closest pair is 1 + 7 = 8, return [1, 7].
	 * 
	 * Method:
	 * sort the array
	 * 
	 * 
	 * total: O(n log n)
	 */
	public static List<Integer> task4_closest(int[] array, int target) {
		// write your solution here
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (array == null || array.length < 2) {
			return result;
		}
		Arrays.sort(array);
		int minDiff = Integer.MAX_VALUE;
		int i = 0, j = array.length - 1;
		int rev1 = 0, rev2 = 0;
		while (i < j) {
			int curSum = array[i] + array[j];
			if (Math.abs(target - curSum) < minDiff) {
				minDiff = Math.abs(target - curSum);
				rev1 = array[i];
				rev2 = array[j];
			}

			if (curSum < target) {
				i++;
			} else {
				j--;
			}
		}
		result.add(rev1);
		result.add(rev2);
		return result;
	}
	
	/*
	 * task5
	 * 2 Sum Smaller
	 * Fair Data Structure
	 * Determine the number of pairs of elements in a given array that sum to a value smaller than the given target number.
	 * Assumptions
	 * The given array is not null and has length of at least 2
	 * Exampes
	 * A = {1, 2, 2, 4, 7}, target = 7, number of pairs is 6({1,2}, {1, 2}, {1, 4}, {2, 2}, {2, 4}, {2, 4})
	 * 
	 * Method:
	 * 1 sort the array in ascending order
	 * 2 for every array[j], find index of element < target - array[j]  // do binary search
	 *   if index != -1, update count;  
	 *    
	 */
	public static void test5() {
		int[] a = {1,2,2,4,7};
		int target = 7;
		int result = task5_smallerPairs(a, target);
		int result2 = task5_2smallerPairs(a, target);
		System.out.println("result = " + result);
		System.out.println("result2 = " + result2);
	}
	
	public static int task5_smallerPairs(int[] array, int target) {
		// write your solution here

		if (array == null || array.length == 0) {
			return 0;
		}
		Arrays.sort(array);
		int count = 0;
		int j = array.length - 1;
		while(j > 0) {
			for(int i = 0; i < j; i ++) {
				if(array[i] + array[j] < target) {
					count ++;
				} else {
					break;
				}
			}
			
			j --;
		} 
		return count;
	} // O(n^2)
	
	
	public static int task5_2smallerPairs(int[] array, int target) {
		if (array == null || array.length == 0) {
			return 0;
		}
		Arrays.sort(array);
		int count = 0;
		
		int j = array.length - 1;
		while(j > 0) {
			int temp = target - array[j];
			int start = 0, end = j - 1;
			// do binary search in a[start..end], find the smallerlargest element's index
			int index = getSmallerLargest(array, start, end, temp);
			if (index != -1) {
				count += (index + 1);
			}
			j --;
		}
		return count;
	}// n log n
	
	public static void test5_1() {
		int[] a = {1,2,3,4,4,5,6,7};
//		int index = getLargerSmallest(a, 0, a.length - 1, -1);
		int index = getSmallerLargest(a, 0, a.length - 1, 1);
		if (index != -1) {
			System.out.println(a[index]);
		}
		System.out.println("index = " + index);
	}
	public static int getLargerSmallest(int[] a, int start, int end, int target) {
		if (target >= a[end]) {
			return -1;
		}
		int left = start; 
		int right = end;
		
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if(a[mid] == target) {
				left = mid;
			} else if (a[mid] < target) {
				left = mid;
			} else {
				//a[mid] > target
				right = mid;
			}
		}
		if (a[left] > target) {
			return left;
		} else {
			return right;
		}
		
	}
	
	public static int getSmallerLargest(int[] a, int start, int end, int target) {
		if (target <= a[start]) {
			return -1;
		}
		int left = start; 
		int right = end;
		
		while(left + 1 < right) {
			int mid = left + (right - left)/2;
			if(a[mid] == target) {
				right = mid;
			} else if (a[mid] < target) {
				left = mid;
			} else {
				//a[mid] > target
				right = mid;
			}
		}
		if (a[right] < target) {
			return right;
		} else {
			return left;
		}
		
	}
	
	/*
	 * task6
	 * 2 Sum 2 Arrays
	 * Fair Data Structure
	 * Given two arrays A and B, determine whether or not there exists a pair of elements, 
	 * one drawn from each array, that sums to the given target number.
	 * Assumptions
	 * The two given arrays are not null and have length of at least 1
	 * Examples
	 * A = {3, 1, 5}, B = {2, 8}, target = 7, return true(pick 5 from A and pick 2 from B)
	 * A = {1, 3, 5}, B = {2, 8}, target = 6, return false
	 */
	public static void test6() {
		int[] a = {-1,0,1};
		int[] b = {7,3};
		int target = 3;
		boolean exist = task6_existSum(a, b, target);
		System.out.println("exist = " + exist);
	}
	
	public static boolean task6_existSum(int[] a, int[] b, int target) {
		// write your solution here
		if (a ==null || a.length == 0 || b == null || b.length == 0) {
			return false;
		}
		// we assume that a.length > b.length
		if (a.length < b.length) {
			return task6_existSum(b, a, target);
		}
		HashSet<Integer> set = new HashSet<Integer>();
		for(Integer i: b) {
			set.add(i);
		}
		
		for(Integer i: a) {
			if (set.contains(target - i)) {
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * task7
	 * 3 Sum
	 * Fair Data Structure
	 * Determine if there exists three elements in a given array that sum to the given target number. 
	 * Return all the triple of values that sums to target.
	 * Assumptions
	 * The given array is not null and has length of at least 3
	 * No duplicate triples should be returned, order of the values in the tuple does not matter
	 * Examples
	 * A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]
	 */
	public static void test7() {
		int[] a = {1, 2, 2, 2,2,2,2,3,3,3,3,3,3,3,3, 3, 2, 4};
		int target = 6;
		List<List<Integer>> result = task7_2_allTriples(a, target);
		System.out.println(result);
	}
	
	public static List<List<Integer>> task7_allTriples(int[] array, int target) {
		// write your solution here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (array == null || array.length == 0) {
			return result;
		}
		// sort the array
		Arrays.sort(array);
		for(int i = 0; i < array.length - 2; i ++) {
			if (i != 0 && array[i] == array[i-1]) {
				continue;
			}
			int j = i + 1; 
			int k = array.length - 1;
			int tempTarget = target - array[i];
			while(j < k) {
				int curSum = array[j] + array[k];
				if (curSum == tempTarget) {
					ArrayList<Integer> line = new ArrayList<Integer>();
					line.add(array[i]);
					line.add(array[j]);
					line.add(array[k]);
					result.add(line);

					j ++;
					k --;
					while(j < k && array[j -1] == array[j]) {
						j ++;
					}
					while( j < k && array[k-1] == array[k]) {
						k --;
					}
				} else if (curSum < tempTarget) {
					while(j + 1 < k && array[j+ 1] == array[j]) {
						j ++;
					}
					j ++;
				} else {
					while( k - 1 > j && array[k-1] == array[k]) {
						k --;
					}
					k --;
				}
			}
		}
		return result;
	}
	
	
	public static List<List<Integer>> task7_2_allTriples(int[] array, int target) {
		// write your solution here
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (array == null || array.length == 0) {
			return result;
		}
		// sort the array
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		for(int i = 0; i < array.length - 2; i ++) {
			if (i != 0 && array[i] == array[i-1]) {
				continue;
			}
			int j = i + 1; 
			int k = array.length - 1;
			int tempTarget = target - array[i];
			while(j < k) {
				int curSum = array[j] + array[k];
				if (curSum == tempTarget) {
					ArrayList<Integer> line = new ArrayList<Integer>();
					line.add(array[i]);
					line.add(array[j]);
					line.add(array[k]);
					result.add(line);
					
					
					j ++;
					k --;
					while(j < k && array[j -1] == array[j]) {
						j ++;
					}
					while( j < k && array[k-1] == array[k]) {
						k --;
					}
				} else if (curSum < tempTarget) {
					while(j + 1 < k && array[j+ 1] == array[j]) {
						j ++;
					}
					j ++;
				} else {
					while( k - 1 > j && array[k-1] == array[k]) {
						k --;
					}
					k --;
				}
			}
		}
		return result;
	}
	
	
	/*
	 * task8
	 * 
	 * 3 Sum 3 Arrays Fair Data Structure
	 * Given three arrays, determine if a set can be made by picking one element from 
	 * each array that sums to the given target number.
	 * Assumptions
	 * The three given arrays are not null and have length of at least 1
	 * Examples
	 * A = {1, 3, 5}, B = {8, 2}, C = {3}, target = 14, return true(pick 3 from A, pick 8 from B and pick 3 from C)
	 * 
	 * 
	 * Reduce toi task6 2 Sum 2 Arrays
	 */
	public static boolean task8_exist3Sum3Arrays(int[] a, int[] b, int[] c, int target) {
		if ( a == null || a.length == 0 || b == null || b.length == 0 || c == null || c.length == 0) {
			return false;
		}
		
		for(int i = 0; i < a.length; i ++) {
			if (task6_existSum(b, c, target - a[i])) {
				return true;
			}
		}
		return false;
	}

	
	/*
	 * task9
	 * 4 Sum
	 * Fair Data Structure
	 * Determine if there exists a set of four elements in a given array that sum to the given target number.
	 * Assumptions
	 * The given array is not null and has length of at least 4
	 * Examples
	 * A = {1, 2, 2, 3, 4}, target = 9, return true(1 + 2 + 2 + 4 = 8)
	 * A = {1, 2, 2, 3, 4}, target = 12, return false
	 */
	public static void test9() {
		int[] array = {3,1,6,3,16,40};
		int target = 23;
		boolean exist = task9_exist4Sum(array, target);
		System.out.println("exist = " + exist);
	}
	
	public static boolean task9_exist4Sum(int[] array, int target) {
		// write your solution here
		if (array == null || array.length == 0) {
			return false;
		}
		Arrays.sort(array);
		for(int i = 0; i < array.length - 3; i ++) {
			for(int j = i + 1; j < array.length - 2; j ++) {
				int start = j + 1; 
				int end = array.length - 1;
				while(start < end) {
					int curSum = array[i] + array[j] + array[start] + array[end];
					if (curSum == target) {
						return true;
					} else if (curSum < target) {
						start ++;
					} else {
						end --;
					}
				}
			}
		}
		return false;
	}
	
	
	
	
	
	

}
