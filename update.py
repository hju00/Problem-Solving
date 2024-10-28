HEADER="""# 
# 백준 & 프로그래머스 문제 풀이 목록

프로그래머스의 경우, 푼 문제 목록에 대한 마이그레이션이 필요합니다.

"""

def main():
    content = ""
    content += HEADER
    
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
                # 추가: 해결 날짜를 예시로 'YYYY-MM-DD'로 기록
                solved_date = "YYYY-MM-DD"  # 실제 해결 날짜를 가져오는 로직 추가 가능
                content += "|{}|{}|[링크]({})|\n".format(category, solved_date, parse.quote(os.path.join(root, file)))
                solveds.append(category)
                print("category : " + category)

    with open("README.md", "w") as fd:
        fd.write(content)
        
if __name__ == "__main__":
    main()
