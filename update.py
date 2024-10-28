#!/usr/bin/env python

import os
import subprocess
from urllib import parse

HEADER = """# 
# 백준 & 프로그래머스 문제 풀이 목록

프로그래머스의 경우, 푼 문제 목록에 대한 마이그레이션이 필요합니다.

"""

def get_commit_date(file_path):
    # Git에서 마지막 커밋 날짜를 가져옴 (YYYY-MM-DD 형식)
    result = subprocess.run(
        ["git", "log", "-1", "--format=%cd", "--date=short", file_path],
        capture_output=True,
        text=True
    )
    return result.stdout.strip()

def main():
    content = HEADER
    directories = []
    solveds = []

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
            if category not in solveds:
                file_path = os.path.join(root, file)
                solved_date = get_commit_date(file_path)  # Git에서 커밋 날짜 가져오기
                content += "|{}|{}|[링크]({})|\n".format(category, solved_date, parse.quote(file_path))
                solveds.append(category)
                print("category : " + category)

    with open("README.md", "w") as fd:
        fd.write(content)
        
if __name__ == "__main__":
    main()
