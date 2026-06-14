import json
with open('src/main/resources/fabric.mod.json', encoding='utf-8') as f:
    content = f.read()
content = content.replace('"version": "\\",', '"version": "1.0.0",')
with open('src/main/resources/fabric.mod.json', 'w', encoding='utf-8') as f:
    f.write(content)
print('Done')
print(content[:200])
