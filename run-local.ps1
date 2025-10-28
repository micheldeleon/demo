$envFile = Join-Path $PSScriptRoot ".env"
Get-Content $envFile |
  Where-Object {$_ -notmatch '^\s*#' -and $_ -match '='} |
  ForEach-Object {
    $name,$value = $_.Split('=',2)
    [Environment]::SetEnvironmentVariable($name.Trim(), $value.Trim(), 'Process')
  }

Write-Host "Using datasource URL:" ([Environment]::GetEnvironmentVariable('SPRING_DATASOURCE_URL','Process'))
Write-Host "Using datasource user:" ([Environment]::GetEnvironmentVariable('SPRING_DATASOURCE_USERNAME','Process'))

Push-Location $PSScriptRoot
try {
  & ".\mvnw.cmd" spring-boot:run
} finally {
  Pop-Location
}
