#!/usr/bin/env python

import os
from urllib import parse

HEADER = """# 
# 백준 & 프로그래머스 & SWEA 문제 풀이 목록

프로그래머스의 경우, 푼 문제 목록에 대한 마이그레이션이 필요합니다.

"""

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
        directory = os.path.basename(os.path.dirname(root))
        
        # 특정 폴더는 건너뜀
        if category == 'images' or directory == '.':
            continue
            
        # 메인 디렉토리 섹션 헤더 추가
        if directory not in directories:
            if directory in ["백준", "프로그래머스", "SWEA"]:
                content += "## 📚 {}\n".format(directory)
            else:
                content += "### 🚀 {}\n".format(directory)
                content += "| 문제번호 | 링크 |\n"
                content += "| ----- | ----- |\n"
            directories.append(directory)

        # 각 파일에 대해 문제 번호가 중복되지 않으면 출력
        for file in files:
            if category not in solveds:
                file_path = os.path.join(root, file)
                content += "|{}|[링크]({})|\n".format(category, parse.quote(file_path))
                solveds.append(category)

    with open("README.md", "w") as fd:
        fd.write(content)
        
if __name__ == "__main__":
    main()
