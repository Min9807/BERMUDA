from flask import Flask, request  # 간단히 플라스크 서버를 만든다
import json # json으로 만들어 줄려고
from ai_module import *
from collections import OrderedDict

app = Flask(__name__)


@app.route("/tospring", methods=['GET', 'POST'])
def spring():
    get_data = request.get_json() # json 데이터로 가져옴. ex) content:'일기본문 내용'
    print(get_data)

    get_content = get_data["content"] # 일기 본문만 가져옴.
    print(get_content)


    get_result = result(get_content) # 일기 본문 분석 결과


    return get_result


if __name__ == '__main__':
    app.run(debug=False, host="localhost", port=5000)