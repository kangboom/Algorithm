import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 1. 장르별 총 재생 횟수 저장
        Map<String, Integer> genrePlaySum = new HashMap<>();
        // 2. 장르별 곡 리스트 저장
        Map<String, ArrayList<Song>> songListMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            // Map API 활용하여 간결하게 작성
            genrePlaySum.put(genre, genrePlaySum.getOrDefault(genre, 0) + play);
            
            songListMap.computeIfAbsent(genre, k -> new ArrayList<>()).add(new Song(i, play));
        }

        // 3. 장르 선정 (총 재생 횟수 내림차순 정렬)
        // Map의 KeySet(장르이름)을 리스트로 만들어 정렬
        List<String> keySet = new ArrayList<>(genrePlaySum.keySet());
        keySet.sort((o1, o2) -> genrePlaySum.get(o2) - genrePlaySum.get(o1));

        List<Integer> answer = new ArrayList<>();

        // 4. 장르별 1, 2등 곡 선정
        for (String genre : keySet) {
            ArrayList<Song> songs = songListMap.get(genre);
            Collections.sort(songs); // Song 클래스의 compareTo 사용

            int count = 0;
            for (Song s : songs) {
                if (count >= 2) break;
                answer.add(s.num);
                count++;
            }
        }

        // 5. 배열 변환
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    static class Song implements Comparable<Song> {
        int num;
        int cnt;

        public Song(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Song o) {
            if (this.cnt == o.cnt) {
                return this.num - o.num; // [중요] 재생 횟수 같으면 고유 번호 오름차순
            }
            return o.cnt - this.cnt; // 재생 횟수 내림차순
        }
    }
}