package com.dicoding.capstoneproject.scan

import com.google.gson.annotations.SerializedName

data class ResponseScan(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("tipe")
	val tipe: String? = null
)

data class CseImageItem(

	@field:SerializedName("src")
	val src: String? = null
)

data class Data(

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("context")
	val context: Context? = null,

	@field:SerializedName("queries")
	val queries: Queries? = null,

	@field:SerializedName("searchInformation")
	val searchInformation: SearchInformation? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null,

	@field:SerializedName("url")
	val url: Url? = null
)

data class Queries(

	@field:SerializedName("request")
	val request: List<RequestItem?>? = null,

	@field:SerializedName("nextPage")
	val nextPage: List<NextPageItem?>? = null
)

data class SearchInformation(

	@field:SerializedName("searchTime")
	val searchTime: Any? = null,

	@field:SerializedName("totalResults")
	val totalResults: String? = null,

	@field:SerializedName("formattedTotalResults")
	val formattedTotalResults: String? = null,

	@field:SerializedName("formattedSearchTime")
	val formattedSearchTime: String? = null
)

data class ArticleItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("headline")
	val headline: String? = null,

	@field:SerializedName("datepublished")
	val datepublished: String? = null
)

data class NextPageItem(

	@field:SerializedName("inputEncoding")
	val inputEncoding: String? = null,

	@field:SerializedName("totalResults")
	val totalResults: String? = null,

	@field:SerializedName("startIndex")
	val startIndex: Int? = null,

	@field:SerializedName("outputEncoding")
	val outputEncoding: String? = null,

	@field:SerializedName("searchTerms")
	val searchTerms: String? = null,

	@field:SerializedName("cx")
	val cx: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("safe")
	val safe: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class Pagemap(

	@field:SerializedName("cse_thumbnail")
	val cseThumbnail: List<CseThumbnailItem?>? = null,

	@field:SerializedName("metatags")
	val metatags: List<MetatagsItem?>? = null,

	@field:SerializedName("cse_image")
	val cseImage: List<CseImageItem?>? = null,

	@field:SerializedName("article")
	val article: List<ArticleItem?>? = null
)

data class Url(

	@field:SerializedName("template")
	val template: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class RequestItem(

	@field:SerializedName("inputEncoding")
	val inputEncoding: String? = null,

	@field:SerializedName("totalResults")
	val totalResults: String? = null,

	@field:SerializedName("startIndex")
	val startIndex: Int? = null,

	@field:SerializedName("outputEncoding")
	val outputEncoding: String? = null,

	@field:SerializedName("searchTerms")
	val searchTerms: String? = null,

	@field:SerializedName("cx")
	val cx: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("safe")
	val safe: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)

data class CseThumbnailItem(

	@field:SerializedName("src")
	val src: String? = null,

	@field:SerializedName("width")
	val width: String? = null,

	@field:SerializedName("height")
	val height: String? = null
)

data class Context(

	@field:SerializedName("title")
	val title: String? = null
)

data class ItemsItem(

	@field:SerializedName("snippet")
	val snippet: String? = null,

	@field:SerializedName("htmlFormattedUrl")
	val htmlFormattedUrl: String? = null,

	@field:SerializedName("htmlTitle")
	val htmlTitle: String? = null,

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("pagemap")
	val pagemap: Pagemap? = null,

	@field:SerializedName("displayLink")
	val displayLink: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("htmlSnippet")
	val htmlSnippet: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("formattedUrl")
	val formattedUrl: String? = null,

	@field:SerializedName("cacheId")
	val cacheId: String? = null
)

data class MetatagsItem(

	@field:SerializedName("citation_keywords")
	val citationKeywords: String? = null,

	@field:SerializedName("dc.source.uri")
	val dcSourceUri: String? = null,

	@field:SerializedName("citation_author_institution")
	val citationAuthorInstitution: String? = null,

	@field:SerializedName("citation_title")
	val citationTitle: String? = null,

	@field:SerializedName("dc.creator.personalname")
	val dcCreatorPersonalname: String? = null,

	@field:SerializedName("citation_journal_title")
	val citationJournalTitle: String? = null,

	@field:SerializedName("dc.format")
	val dcFormat: String? = null,

	@field:SerializedName("dc.rights")
	val dcRights: String? = null,

	@field:SerializedName("citation_date")
	val citationDate: String? = null,

	@field:SerializedName("dc.type.articletype")
	val dcTypeArticletype: String? = null,

	@field:SerializedName("dc.identifier.uri")
	val dcIdentifierUri: String? = null,

	@field:SerializedName("citation_issn")
	val citationIssn: String? = null,

	@field:SerializedName("dc.identifier.pagenumber")
	val dcIdentifierPagenumber: String? = null,

	@field:SerializedName("dc.source")
	val dcSource: String? = null,

	@field:SerializedName("dc.type")
	val dcType: String? = null,

	@field:SerializedName("citation_language")
	val citationLanguage: String? = null,

	@field:SerializedName("citation_pdf_url")
	val citationPdfUrl: String? = null,

	@field:SerializedName("citation_lastpage")
	val citationLastpage: String? = null,

	@field:SerializedName("dc.description")
	val dcDescription: String? = null,

	@field:SerializedName("citation_journal_abbrev")
	val citationJournalAbbrev: String? = null,

	@field:SerializedName("citation_author")
	val citationAuthor: String? = null,

	@field:SerializedName("dc.date.created")
	val dcDateCreated: String? = null,

	@field:SerializedName("citation_abstract_html_url")
	val citationAbstractHtmlUrl: String? = null,

	@field:SerializedName("citation_issue")
	val citationIssue: String? = null,

	@field:SerializedName("dc.date.datesubmitted")
	val dcDateDatesubmitted: String? = null,

	@field:SerializedName("dc.date.issued")
	val dcDateIssued: String? = null,

	@field:SerializedName("dc.identifier.doi")
	val dcIdentifierDoi: String? = null,

	@field:SerializedName("citation_firstpage")
	val citationFirstpage: String? = null,

	@field:SerializedName("viewport")
	val viewport: String? = null,

	@field:SerializedName("citation_doi")
	val citationDoi: String? = null,

	@field:SerializedName("dc.title")
	val dcTitle: String? = null,

	@field:SerializedName("dc.date.modified")
	val dcDateModified: String? = null,

	@field:SerializedName("dc.source.issue")
	val dcSourceIssue: String? = null,

	@field:SerializedName("dc.identifier")
	val dcIdentifier: String? = null,

	@field:SerializedName("dc.subject")
	val dcSubject: String? = null,

	@field:SerializedName("citation_volume")
	val citationVolume: String? = null,

	@field:SerializedName("dc.language")
	val dcLanguage: String? = null,

	@field:SerializedName("dc.source.volume")
	val dcSourceVolume: String? = null,

	@field:SerializedName("dc.source.issn")
	val dcSourceIssn: String? = null,

	@field:SerializedName("gs_meta_revision")
	val gsMetaRevision: String? = null,

	@field:SerializedName("application-name")
	val applicationName: String? = null,

	@field:SerializedName("msapplication-starturl")
	val msapplicationStarturl: String? = null,

	@field:SerializedName("theme-color")
	val themeColor: String? = null,

	@field:SerializedName("apple-mobile-web-app-status-bar-style")
	val appleMobileWebAppStatusBarStyle: String? = null,

	@field:SerializedName("apple-mobile-web-app-capable")
	val appleMobileWebAppCapable: String? = null,

	@field:SerializedName("apple-mobile-web-app-title")
	val appleMobileWebAppTitle: String? = null,

	@field:SerializedName("mobile-web-app-capable")
	val mobileWebAppCapable: String? = null,

	@field:SerializedName("msapplication-navbutton-color")
	val msapplicationNavbuttonColor: String? = null,

	@field:SerializedName("citation_reference")
	val citationReference: String? = null,

	@field:SerializedName("og:image")
	val ogImage: String? = null,

	@field:SerializedName("og:type")
	val ogType: String? = null,

	@field:SerializedName("article:published_time")
	val articlePublishedTime: String? = null,

	@field:SerializedName("og:image:width")
	val ogImageWidth: String? = null,

	@field:SerializedName("twitter:card")
	val twitterCard: String? = null,

	@field:SerializedName("og:site_name")
	val ogSiteName: String? = null,

	@field:SerializedName("og:title")
	val ogTitle: String? = null,

	@field:SerializedName("og:image:height")
	val ogImageHeight: String? = null,

	@field:SerializedName("bingbot")
	val bingbot: String? = null,

	@field:SerializedName("msapplication-tileimage")
	val msapplicationTileimage: String? = null,

	@field:SerializedName("og:description")
	val ogDescription: String? = null,

	@field:SerializedName("twitter:creator")
	val twitterCreator: String? = null,

	@field:SerializedName("article:publisher")
	val articlePublisher: String? = null,

	@field:SerializedName("twitter:site")
	val twitterSite: String? = null,

	@field:SerializedName("og:locale")
	val ogLocale: String? = null,

	@field:SerializedName("og:url")
	val ogUrl: String? = null,

	@field:SerializedName("twitter:text:title")
	val twitterTextTitle: String? = null,

	@field:SerializedName("article:modified_time")
	val articleModifiedTime: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("twitter:image")
	val twitterImage: String? = null,

	@field:SerializedName("datemodified")
	val datemodified: String? = null,

	@field:SerializedName("datepublished")
	val datepublished: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("article:section")
	val articleSection: String? = null,

	@field:SerializedName("og:image:alt")
	val ogImageAlt: String? = null,

	@field:SerializedName("twitter:title")
	val twitterTitle: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("twitter:description")
	val twitterDescription: String? = null,

	@field:SerializedName("publisher")
	val publisher: String? = null,

	@field:SerializedName("msapplication-tilecolor")
	val msapplicationTilecolor: String? = null,

	@field:SerializedName("twitter:domain")
	val twitterDomain: String? = null,

	@field:SerializedName("article:author")
	val articleAuthor: String? = null,

	@field:SerializedName("fb:app_id")
	val fbAppId: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("distribution")
	val distribution: String? = null,

	@field:SerializedName("google-play-app")
	val googlePlayApp: String? = null
)
