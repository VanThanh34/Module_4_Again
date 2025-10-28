package com.example.bai1.repository;

import com.example.bai1.entity.Player;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRepository implements IPlayerRepository {
    private static final List<Player> playerList = new ArrayList<>();

    static {
        playerList.add(new Player(1, "Wayne Rooney", LocalDate.of(1985, 10, 24),
                "Cựu đội trưởng, ghi bàn nhiều nhất lịch sử MU", "Tiền đạo",
                "https://file.hstatic.net/200000293662/article/wayne_rooney_nguoi_giu_lua_79ca7c6a83424964a011aa963fbe9630.jpg"));

        playerList.add(new Player(2, "Cristiano Ronaldo", LocalDate.of(1985, 2, 5),
                "5 Quả bóng vàng, huyền thoại MU và Real Madrid", "Tiền đạo",
                "https://media.vov.vn/sites/default/files/styles/large/public/2021-08/CR7%202.jpg"));

        playerList.add(new Player(3, "Ryan Giggs", LocalDate.of(1973, 11, 29),
                "Cầu thủ khoác áo MU nhiều nhất lịch sử", "Tiền vệ trái",
                "https://www.elleman.vn/wp-content/uploads/2018/11/25/Ryan-Giggs-ELLE-Man-3.jpg"));

        playerList.add(new Player(4, "Paul Scholes", LocalDate.of(1974, 11, 16),
                "Huyền thoại tiền vệ trung tâm, nổi tiếng với cú sút xa", "Tiền vệ",
                "https://vcdn1-thethao.vnecdn.net/2018/02/19/PaulScholesfacts-1519008428.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=E0oYaHMNGWPErobY19OlIQ"));

        playerList.add(new Player(5, "Rio Ferdinand", LocalDate.of(1978, 11, 7),
                "Trung vệ thép, từng là đội trưởng MU", "Hậu vệ",
                "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRPyyNO3ps4eHvRY3TvNAO9rV9yk1M6WowrKUANLVTqGm8GKyeDLrVslo_Hi6D1i3TfxDVJKbzs7FaTOpwjpum-ekLzx7e4DTqP-GuvuQ"));
        playerList.add(new Player(6, "Văn Thành", LocalDate.of(1978, 11, 7),
                "Trung vệ thép, từng là đội trưởng MU", "Hậu vệ",
                "https://www.facebook.com/photo/?fbid=2242844166114454&set=a.297697513962472"));
    }

    @Override
    public List<Player> findAll() {
        return playerList;
    }

    @Override
    public Player findById(int id) {
        for (Player player : playerList) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (Player player : playerList) {
            if (player.getId() == id) {
                playerList.remove(player);
                break;
            }
        }
    }
}
