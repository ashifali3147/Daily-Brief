package com.tlw.dailybrief.data.mapper

import com.tlw.dailybrief.data.model.Result
import com.tlw.dailybrief.domain.model.News

fun Result.toDomain(workType: String): News {
    return News(
        article_id = article_id,
        ai_org = ai_org,
        ai_region = ai_region,
        ai_summary = ai_summary,
        ai_tag = ai_tag,
        category = category,
        content = content,
        country = country,
        creator = creator,
        datatype = datatype,
        description = description,
        duplicate = duplicate,
        fetched_at = fetched_at,
        image_url = image_url,
        keywords = keywords,
        language = language,
        link = link,
        pubDate = pubDate,
        pubDateTZ = pubDateTZ,
        sentiment = sentiment,
        sentiment_stats = sentiment_stats,
        source_icon = source_icon,
        source_id = source_id,
        source_name = source_name,
        source_priority = source_priority,
        source_url = source_url,
        title = title,
        video_url = video_url as? String, // safe cast
        workType = workType,
    )
}