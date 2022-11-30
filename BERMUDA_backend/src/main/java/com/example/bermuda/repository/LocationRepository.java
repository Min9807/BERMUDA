package com.example.bermuda.repository;

import com.example.bermuda.domain.Diary;
import com.example.bermuda.domain.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    public Boolean existsByAddressName(String addressName);

//    public Optional<Location> findByLatitudeAndLongitude(Double latitude, Double longitude);
    public Optional<Location> findByAddressName(String addressName);

//    @Query(value = "SELECT *," +
//            "(6371*acos(cos(radians(37.4685225))*cos(radians(P_LAT))*cos(radians(P_LON)	- radians(126.8943311))+sin(radians(37.4685225))*sin(radians(P_LAT)))) AS distance " +
//            "FROM MAP_INFOHAVING distance <= 0.3 " +
//            "ORDER BY distance LIMIT 0,300", nativeQuery = true)
//    public List<Location> findNearLocationByLatitudeAndLongitude(Double latitude, Double longitude);
}
