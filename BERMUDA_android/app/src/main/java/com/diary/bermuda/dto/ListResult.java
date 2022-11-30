package com.diary.bermuda.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ListResult<T> extends CommonResult {
    // List 형태로 여러 데이터를 받음
    // CommonResult를 상속받아 API 요청 결과도 같이 출력
    @SerializedName("list")
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ListResult{" +
                "list=" + list +
                '}';
    }



}