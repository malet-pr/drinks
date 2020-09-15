package com.example.drinks.utils;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.example.drinks.R;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class Functions {
    
    public static void ChangeMainFragment(FragmentActivity fragmentActivity,Fragment fragment,String TAG){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, fragment,TAG)
                .commit();
    }
    
    public static void changeMainFragmentWithBack(FragmentActivity fragmentActivity, Fragment fragment,String TAG){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, fragment,TAG)
                .addToBackStack(null)
                .commit();
    }
    
    public static String convert(String str) {
        char ch[] = str.toCharArray();
        for(int i = 0; i < str.length(); i++) {
            if(i == 0 && ch[i] != ' ' ||
                    ch[i] != ' ' && ch[i - 1] == ' ') {
                if(ch[i] >= 'a' && ch[i] <= 'z') {
                    ch[i] = (char)(ch[i] - 'a' + 'A');
                }
            } else if(ch[i] >= 'A' && ch[i] <= 'Z')
                ch[i] = (char)(ch[i] + 'a' - 'A');
        }
        String st = new String(ch);
        return st;
    }
    
}
