package com.diary.bermuda.dto;

import com.google.gson.annotations.SerializedName;

public class FavoriteDto {

    public static class favoriteDiary{
        @SerializedName("diaryId")
        private int diaryId;

        @SerializedName("favorite_diary")
        private boolean favorite_diary;

        public int getDiaryId() {
            return diaryId;
        }

        public void setDiaryId(int diaryId) {
            this.diaryId = diaryId;
        }

        public boolean isFavorite_diary() {
            return favorite_diary;
        }

        public void setFavorite_diary(boolean favorite_diary) {
            this.favorite_diary = favorite_diary;
        }
    }

}
