package com.example.mingxiu.bottomnavigation;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TaboneFragment extends Fragment {
    public static Button submitBtn;
    private Button scbtn;
    private  EditText editTextNumber;
    private  EditText editTextName;
    private List<String> myData;
    private String preName = "";
    private View view;
    private View bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private ListView listView;
    private Toolbar myToolbar;
    private View bottomView;
    private Button btnDone;
    public static  Dialog mBottomSheetDialog;
    public static GradientDrawable scbDrawable;
    public static int scColor = 100;
    private String colorName = "";
    private DatabaseHelper databaseHelper;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private static int code = 1;
    private boolean isInsertData = false;
    private boolean isSend = false;
    public TaboneFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        databaseHelper = new DatabaseHelper(this.getContext());
        scbtn = (Button) view.findViewById(R.id.scbtn);
        submitBtn = (Button) view.findViewById(R.id.submitBtn);

        editTextNumber = (EditText) view.findViewById(R.id.editTextNumber);
        editTextName = (EditText) view.findViewById(R.id.editTextName);

        scbDrawable = (GradientDrawable) scbtn.getBackground();

        checkKeyBoardUp();
        bottomView = getActivity().getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        btnDone = (Button) bottomView.findViewById(R.id.btnDone);
        listView = (ListView) bottomView.findViewById(R.id.listView1);

        final String[] listValue = new String[]{"Orange", "Yellow", "Green", "Red", "Blue", "Purple"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(bottomView.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, listValue){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view_list = super.getView(position, convertView, parent);
                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view_list.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.GRAY);
                tv.setGravity(Gravity.CENTER);
                return view_list;
            }
        };

        listView.setAdapter(adapter);

        // ListView on item selected listener.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                switch (position) {

                    case 0:
                        scColor = 0;
                        scbDrawable.setColor(Color.parseColor("#f4822c"));
                        break;
                    case 1:
                        scColor = 1;
                        scbDrawable.setColor(Color.parseColor("#f4f12e"));
                        break;
                    case 2:
                        scColor = 2;
                        scbDrawable.setColor(Color.parseColor("#0eb757"));
                        break;
                    case 3:
                        scColor = 3;
                        scbDrawable.setColor(Color.parseColor("#e8370b"));
                        break;
                    case 4:
                        scColor = 4;
                        scbDrawable.setColor(Color.parseColor("#0485d6"));
                        break;
                    case 5:
                        scColor = 5;
                        scbDrawable.setColor(Color.parseColor("#9f04ce"));
                        break;

                }
                for (int i = 0; i < listView.getChildCount(); i++) {
                    View view1 = listView.getChildAt(i);
                    TextView tv = (TextView) view1.findViewById(android.R.id.text1);
                    if(position == i ){


                        tv.setTextColor(Color.rgb(0,0,0));
                    }else{
                        tv.setTextColor(Color.GRAY);
                    }
                }
                validateInput();
                //Toast.makeText(view.getContext(), listValue[position] + scColor , Toast.LENGTH_SHORT).show();
            }
        });


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateInput();
            }
        };

        editTextName.addTextChangedListener(textWatcher);
        editTextNumber.addTextChangedListener(textWatcher);
        validateInput();
        return view;
    }


    public void checkKeyBoardUp(){
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                view.getWindowVisibleDisplayFrame(r);
                int heightDiff = view.getRootView().getHeight() - (r.bottom - r.top);
                if(heightDiff > 100){
                    MainActivity.bottomNavigation.setVisibility(View.INVISIBLE);
                } else {
                    MainActivity.bottomNavigation.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void validateInput() {
        String name_tx = editTextName.getText().toString().trim();
        int numberLength = editTextNumber.getText().length();

        if (!name_tx.isEmpty() && name_tx.length() != 0 && !name_tx.equals("") && name_tx != null && numberLength == 4 && scColor != 100) {
            submitBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            submitBtn.setEnabled(true);
        } else {
            submitBtn.setBackgroundColor(Color.GRAY);
            submitBtn.setEnabled(false);
        }
    }

    public static int getCode(){
        return code;
    }
    public static void setCode(int c){
        code = c;
    }


    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isInsertData = false;
        isSend = false;
        if(code == 2){
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, MainActivity.searchFragment2).commit();


        } else if(code == 3){
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, MainActivity.searchFragment3).commit();


        }


        //Toast.makeText(getContext(),code + "",Toast.LENGTH_SHORT).show();

        mBottomSheetDialog = new Dialog(this.getActivity(), R.style.MaterialDialogSheet);
       // mBottomSheetDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mBottomSheetDialog.setContentView(bottomView);
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    //Toast.makeText(getContext(),"great",Toast.LENGTH_SHORT).show();
//                    scColor = 100;
//                    scbDrawable.setColor(Color.parseColor("#ffffff"));
                    mBottomSheetDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        scbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.show();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (scColor){
                    case 100:
                        colorName = "White";
                        break;
                    case 0:
                        colorName = "Orange";
                        break;
                    case 1:
                        colorName = "Yellow";
                        break;
                    case 2:
                        colorName = "Green";
                        break;
                    case 3:
                        colorName = "Red";
                        break;
                    case 4:
                        colorName = "Blue";
                        break;
                    case 5:
                        colorName = "Purple";
                        break;
                }
                if(!isInsertData){
                    boolean test = databaseHelper.insertData(editTextName.getText().toString().trim(),editTextNumber.getText().toString().trim(),colorName);

                    if(test){
                        viewAll();
                    } else {
                        Toast.makeText(getContext(),"Can not Insert",Toast.LENGTH_SHORT).show();
                    }
                    isInsertData = true;
                }

                code = 2;
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, MainActivity.searchFragment2).commit();



            }
        });


    }

    private void viewAll(){

        Cursor res = databaseHelper.getAllData();
        if(res.getCount() == 0){
            showMessage("Error","Not found");
            return;
        }

        StringBuffer stringBuffer = new StringBuffer();
        SearchFragment2.names.clear();
        SearchFragment2.codes.clear();
        SearchFragment2.colors.clear();
        while(res.moveToNext()){
            String name = res.getString(1);
            String code = res.getString(2);
            String color = res.getString(3);

            //((SearchFragment2)getFragmentManager().findFragmentByTag(SearchFragment2.TAG)).setData(name);
            SearchFragment2.setData(name,code,color);



        }

    }

    private void showMessage(String title, String str) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.getContext());
        alertDialog.setCancelable(true);
        alertDialog.setTitle(title);
        alertDialog.setMessage(str);
        alertDialog.show();

    }


}