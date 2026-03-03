package com.tlw.dailybrief.data.mapper

import com.tlw.dailybrief.data.model.Result
import com.tlw.dailybrief.domain.model.News

fun Result.toDomain(workType: String): News {
    return News(
        article_id = article_id,
        ai_org = ai_org.orEmpty(),
        ai_region = ai_region.orEmpty(),
        ai_summary = ai_summary.orEmpty(),
        ai_tag = ai_tag.orEmpty(),
        category = category.orEmpty(),
        content = content.orEmpty(),
        country = country.orEmpty(),
        creator = creator.orEmpty(),
        datatype = datatype.orEmpty(),
        description = description.orEmpty(),
        duplicate = duplicate,
        fetched_at = fetched_at.orEmpty(),
        image_url = image_url.orEmpty(),
        keywords = keywords.orEmpty(),
        language = language.orEmpty(),
        link = link.orEmpty(),
        pubDate = pubDate.orEmpty(),
        pubDateTZ = pubDateTZ.orEmpty(),
        sentiment = sentiment.orEmpty(),
        sentiment_stats = sentiment_stats.orEmpty(),
        source_icon = source_icon.orEmpty(),
        source_id = source_id.orEmpty(),
        source_name = source_name.orEmpty(),
        source_priority = source_priority ?: 0,
        source_url = source_url.orEmpty(),
        title = title.orEmpty(),
        video_url = video_url as? String, // safe cast
        workType = workType,
    )
}
