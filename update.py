#!/usr/bin/env python

import os
from urllib import parse
from datetime import datetime

HEADER = """# 
# 백준 & 프로그래머스 문제 풀이 목록

프로그래머스의 경우, 푼 문제 목록에 대한 마이그레이션이 필요합니다.

"""

def get_solved_date(file_path):
    # 파일의 마지막 수정 시간을 가져와 해결 날짜로 사용
    timestamp = os.path.getmtime(file_path)
    solved_date = datetime.fromtimestamp(timestamp).strftime("%Y-%m-%d")
    return solved_date

def main():
    content = ""
    content += HEADER
    
    directories = []
    solved_files = []

    for root, dirs, files in os.walk("."):
        dirs.sort()
        if root == '.':
            for dir in ('.git', '.github'):
                try:
                    dirs.remove(dir)
                except ValueError:
                    pass
            continue

        category = os.path.basename(root)
        
        if category == 'images':
            continue
        
        directory = os.path.basename(os.path.dirname(root))
        
        if directory == '.':
            continue
            
        if directory not in directories:
            if directory in ["백준", "프로그래머스"]:
                content += "## 📚 {}\n".format(directory)
            else:
                content += "### 🚀 {}\n".format(directory)
                content += "| 문제번호 | 해결 날짜 | 링크 |\n"
                content += "| ----- | --------- | ----- |\n"
            directories.append(directory)

        for file in files:
            file_path = os.path.join(root, file)
            # 이미 처리한 파일인지 확인하여 중복 방지
            if file_path not in solved_files:
                solved_date = get_solved_date(file_path)  # 해결 날짜 가져오기
                content += "|{}|{}|[링크]({})|\n".format(category, solved_date, parse.quote(file_path))
                solved_files.append(file_path)  # 중복 방지를 위해 추가
                print("Processed file:", file_path)

    with open("README.md", "w") as fd:
        fd.write(content)
        
if __name__ == "__main__":
    main()
