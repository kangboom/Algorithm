import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Integer> answer = new ArrayList<>();
        int N = genres.length;
        
        Map<String, Genre> genreMap = new HashMap<>();
        Map<String, ArrayList<Song>> songListMap = new HashMap<>();
        List<Genre> genreList = new ArrayList<>();
        
        for(int i=0; i<N; i++){
            String str = genres[i];
            int cnt = plays[i];
            
            if(!genreMap.containsKey(str)){
                Genre newGenre = new Genre(str, cnt);
                genreMap.put(str, newGenre);
                genreList.add(newGenre);
            } else {
                genreMap.get(str).sum += cnt;
            }
            
            if(!songListMap.containsKey(str)){
                ArrayList<Song> songList = new ArrayList<>();
                songList.add(new Song(i, cnt));
                songListMap.put(str, songList);
            } else {
                songListMap.get(str).add(new Song(i, cnt));
            }
        }

        Collections.sort(genreList);
        for(Genre genre : genreList){
            String name = genre.name;
            ArrayList<Song> songList = songListMap.get(name);
            Collections.sort(songList);
            
            int max = 2;
            for(Song song : songList){
                if(max == 0) break;
                answer.add(song.num);
                max--;
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    static class Genre implements Comparable<Genre>{
        String name;
        int sum;
        
        public Genre(String name, int sum){
            this.name = name;
            this.sum = sum;
        }
        
        @Override
        public int compareTo(Genre o){
            return o.sum - this.sum;
        }
    }
    
    static class Song implements Comparable<Song> {
        int num;
        int cnt;
        
        public Song(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Song o){
            return o.cnt - this.cnt;
        }
    }
}