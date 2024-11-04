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
        
        # Skip specific folders that don't need to be processed
        if category == 'images' or directory == '.':
            continue
            
        # Add main directory section headers
        if directory not in directories:
            if directory in ["백준", "프로그래머스", "SWEA"]:
                content += "## 📚 {}\n".format(directory)
            else:
                content += "### 🚀 {}\n".format(directory)
                content += "| 문제번호 | 링크 |\n"
                content += "| ----- | ----- |\n"
            directories.append(directory)

        # Append problem details for each file in the directory
        for file in files:
            file_path = os.path.join(root, file)
            content += "|{}|[링크]({})|\n".format(category, parse.quote(file_path))

    with open("README.md", "w") as fd:
        fd.write(content)
        
if __name__ == "__main__":
    main()
