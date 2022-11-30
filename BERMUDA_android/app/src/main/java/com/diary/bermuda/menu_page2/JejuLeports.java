package com.diary.bermuda.menu_page2;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.diary.bermuda.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class JejuLeports extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag2_jeju_leports, container, false);
        StrictMode.enableDefaults();

        TextView status1 = (TextView)view.findViewById(R.id.result); //파싱된 결과확인!

        boolean initem = false, inAddr1 = false, inTitle = false, inCat1 = false, inCat2 = false, inCat3 = false, inContentid = false, inContenttypeid = false;

        String addr1 = null, title = null, cat1 = null, cat2 = null, cat3 = null, contentid = null, contenttypeid = null;


        String first = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey=3pVWxOczmyxGKCn4OaKaf7QowzdpCgfeB64B%2Fw4RopLskhamZ4VJ1Ij9p0nvV%2BBw0SRwCV5A251lZgcpsMTXKw%3D%3D&pageNo=1&numOfRows=10000&MobileApp=AppTest&MobileOS=ETC&arrange=B&";
        String contentTypeId="28";          //카테고리 선택 (현재: 레포츠)
        String areaCode = "39";              //지역 선택 (현재: 제주)
        String perfect = first + "contentTypeId=" + contentTypeId + "&areaCode=" + areaCode + "&listYN=Y";
        try{
            URL url = new URL(perfect); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println(parserEvent);
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){

                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
//                        System.out.println("START_TAGSTART_TAGSTART_TAG");

                        if(parser.getName().equals("addr1")){ //title 만나면 내용을 받을수 있게 하자
                            inAddr1 = true;
                        }
                        if(parser.getName().equals("title")){ //title 만나면 내용을 받을수 있게 하자
                            inTitle = true;
                        }
                        if(parser.getName().equals("cat1")){ //title 만나면 내용을 받을수 있게 하자
                            inCat1 = true;
                        }
                        if(parser.getName().equals("cat2")){ //title 만나면 내용을 받을수 있게 하자
                            inCat2 = true;
                        }
                        if(parser.getName().equals("cat3")){ //title 만나면 내용을 받을수 있게 하자
                            inCat3 = true;
                        }
                        if(parser.getName().equals("contentid")){ //title 만나면 내용을 받을수 있게 하자
                            inContentid = true;
                        }
                        if(parser.getName().equals("contenttypeid")){ //title 만나면 내용을 받을수 있게 하자
                            inContenttypeid = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;


                    case XmlPullParser.TEXT://parser가 내용에 접근했을때

                        if(inAddr1){ //isTitle이 true일 때 태그의 내용을 저장.
                            addr1 = parser.getText();
                            inAddr1 = false;
                        }
                        if(inTitle){ //isTitle이 true일 때 태그의 내용을 저장.
                            title = parser.getText();
                            inTitle = false;
                        }
                        if(inCat1){ //isTitle이 true일 때 태그의 내용을 저장.
                            cat1 = parser.getText();
                            inCat1 = false;
                        }
                        if(inCat2){ //isTitle이 true일 때 태그의 내용을 저장.
                            cat2 = parser.getText();
                            inCat2 = false;
                        }
                        if(inCat3){ //isTitle이 true일 때 태그의 내용을 저장.
                            cat3 = parser.getText();
                            inCat3 = false;
                        }
                        if(inContentid){ //isTitle이 true일 때 태그의 내용을 저장.
                            contentid = parser.getText();
                            inContentid = false;
                        }
                        if(inContenttypeid){ //isTitle이 true일 때 태그의 내용을 저장.
                            contenttypeid = parser.getText();
                            inContenttypeid = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            // cat1, cat2, cat3, contentid, contenttypeid를 추가한 이유는 스프링부트에게 해당 정보를 넘겨주기 위해서
                            // 좋아요 버튼을 눌렀을때, 5개 정보를 보내주고 스프링부트는 5개정보를 디비에 저장

                            status1.setText(status1.getText()+"이름 : "+ title + "\n주소 : "+ addr1 +"\n\n");
                            initem = false;

                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
            status1.setText(" 해당정보를 불러올 수 없습니다.");
        }

        return view;
    }
}