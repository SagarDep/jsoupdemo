package me.mikasa.science.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import me.mikasa.science.R;
import me.mikasa.science.base.BaseToolbarActivity;

public class SearchActivity extends BaseToolbarActivity {
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.iv_search)
    ImageView search;
    @BindView(R.id.iv_delete)
    ImageView iv_delete;


    @Override
    protected void initData() {
        Intent intent=getIntent();
        String s=intent.getStringExtra("hint");
        et_search.setText(s);
        et_search.setSelection(s.length());
    }

    @Override
    protected void initView() {
        TextWatcher watcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(et_search.getText().toString())){
                    iv_delete.setVisibility(View.VISIBLE);
                }else {
                    iv_delete.setVisibility(View.INVISIBLE);
                }
            }
        };
        et_search.addTextChangedListener(watcher);
    }

    @Override
    protected void initListener() {
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    @Override
    protected int setLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
    private void search(){
        String s=et_search.getText().toString();
        if (s.length()>0){
            String encode=null;
            try {
                encode= URLEncoder.encode(s,"utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            String url="https://baike.baidu.com/item/"+encode;
            Intent intent=new Intent(SearchActivity.this,WebViewActivity.class);
            intent.putExtra("url",url);
            startActivity(intent);
            finish();
        }
    }
}
