# -*- coding: utf-8 -*-
import numpy as np
import json
import re


from konlpy.tag import * ; okt = Okt()


class diary:
    def __init__(self, text):
        self.text = text
        self.stop = 0
        self.word_scores = []

    def prep(self):
        self.text = re.sub('[.!]', ' ', self.text)
        self.test = re.sub('[^가-힣0-9a-zA-Z\\s]', '', self.text)
        self.text = re.sub(' +', ' ', self.text)

        return self.text

    def tokenizer(self):
        self.word_list = okt.morphs(self.text)
        self.word_list = [notoneword for notoneword in self.word_list if not len(notoneword) == 1]

        return self.word_list

    def get_score(self):
        with open('./SentiWord_info.json', encoding='utf-8-sig', mode='r') as f:
            data = json.load(f)
        score = 0

        for word in self.word_list:
            for i in range(0, len(data)):
                if data[i]['word'] == word:
                    s_word = data[i]['polarity']
                    self.word_scores.append(int(s_word))

        return self.word_scores

    def get_ratio(self):
        ent_score = 0

        ent_score = np.sum(self.word_scores)
        length = np.log10(len(self.word_scores)) + 1

        try:
            self.ratio = round(ent_score / length, 2)
        except:
            self.ratio = 0

        return self.ratio


# class result:
#     def __init__(self, text):
#         self.text = text
#         res = diary(self.text)
#         res.prep()
#         res.tokenizer()
#         res.get_score()
#         ratio = res.get_ratio()
#         feel = ''
#         if ratio < -1:
#             feel = 'Dark'
#         elif ratio > 1:
#             feel = 'Bright'
#         else:
#             feel = 'Mid'
#         print(feel)
#         print(type(feel))
#         return feel

def result(text):
    res = diary(text)
    res.prep()
    res.tokenizer()
    res.get_score()
    ratio = res.get_ratio()
    if ratio < -1:
        feel = 'Dark'
    elif ratio > 1:
        feel = 'Bright'
    else:
        feel = 'Mid'
    return feel
# result("오늘은 부산에 여행온지 2일차가 되었다. 친구들과 남포동에 택시를 타고 갔더니 어제 마신 술이 올라오는 것 같아서 어지러웠다. 그래도 도착해서 점심으로 먹은 피자와 파스타는 엄청 맛있었다. 오후에는 시장 구경을 다니다가 잠시 쉬기 위해 만화카페에 들어갔다. 만화카페에 고양이가 있었는데 너무 귀여워서 사진을 찍었다. 고양이는 앞으로 세상을 구할 것이다. 저녁시간이 다 되어서 만화카페를 나왔을 때 비가 내리기 시작했다. 아무래도 장마철에 여행을 와서 그런지 장대비가 쏟아져 내렸다. 한 여름이였지만 날이 너무 시원하고 옷은 축축했다. 기분이 좋은데 나빴달까. 이 후에 저녁 식사를 위해 삼겹살 집에 다녀왔다. 가게에서는 특이한 술잔을 사용하고 있어서 신기했다. 낮에 숙취를 겪었어도 여행 내내 술이 빠질 수는 없다. 이런저런 일들이 많았지만 참 즐거운 하루였다!")