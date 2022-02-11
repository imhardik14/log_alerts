package com.hardik4u.log_alerts;


public class Solution {

	public String solution(String S) {
        // write your code in Java SE 8
		
		int start = 0;
		int end = S.length();
		
		while (start < end) {
			if(S.charAt(start) != S.charAt(end)) {
				
				if(S.charAt(start) == '?' && S.charAt(end) != '?')
				{
					
				}
				
			}
			start++;
			end --;
		}
		
		return "";
    }
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		System.out.println(s.solution("?ab??a"));
	}
}
