content = open('src/main/java/com/example/crystalautoclicker/config/CrystalConfig.java', encoding='utf-8').read()
# ลบทุกบรรทัดที่มี REQUIRE_CRYSTAL_IN_HAND, MIN_PLACE_DISTANCE, MAX_PLACE_DISTANCE ซ้ำ
lines = content.split('\n')
seen = set()
result = []
skip_keys = ['REQUIRE_CRYSTAL_IN_HAND', 'MIN_PLACE_DISTANCE', 'MAX_PLACE_DISTANCE']
for line in lines:
    key = None
    for k in skip_keys:
        if k in line and 'static' in line:
            key = k
            break
    if key:
        if key not in seen:
            seen.add(key)
            result.append(line)
    else:
        result.append(line)
open('src/main/java/com/example/crystalautoclicker/config/CrystalConfig.java', 'w', encoding='utf-8').write('\n'.join(result))
print('Done')
